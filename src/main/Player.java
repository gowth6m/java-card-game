package main;

public class Player implements Runnable{
    private final int playerNumber;
    private final CardHand hand;
    private final CardDeck deck;
    private final GameLogger logger;

    /**
     * Constructs an instance of player with their initial hand and deck values.
     * @param h CardDeck representing initial player hand.
     * @param d CardDeck representing initial player deck.
     * @param num Integer representing the player number.
     */
    public Player(CardHand h, CardDeck d, int num) {
        this.hand = h;
        this.deck = d;
        playerNumber = num;
        logger = new GameLogger();
        logger.writeToFile("player", playerNumber, "player " + playerNumber + " initial hand " + hand.getStringOfCardValues());
    }

    /**
     * Loop of the actual game for a player.
     * (check if deck is empty and if game is over) -> Draws card -> Discards card
     */
    public void run(){
        while(CardGame.winningPlayer.get() == 0){
            if(hasWon()){
                CardGame.winningPlayer.set(playerNumber);
                System.out.println("player "+ playerNumber + " wins");
            } else if (deck.isEmpty()){
                synchronized (this){
                    try {
                        wait();
                    } catch (InterruptedException ignored) {}
                }
            } else {
                drawCard();
                discardCard();
                synchronized (CardGame.getNextPlayer(this)){
                    CardGame.getNextPlayer(this).notify();
                }
                // Logging
                logger.writeToFile("player", playerNumber,"player " + playerNumber + " current hand is " + hand.getStringOfCardValues());
                // TODO REMOVE THIS BEFORE SUBMIT
                logger.writeToFile("player", playerNumber, "--------------------------------");
            }
        }
        // logs the final deck in a separate deck text file.
        logger.writeToFile("deck", playerNumber,"deck" + playerNumber + " contents: " + deck.getStringOfCardValues());

        if(CardGame.winningPlayer.get() == playerNumber){
            logger.writeToFile("player", playerNumber,"player " + playerNumber + " wins");
        } else {
            logger.writeToFile("player", playerNumber, "player " + CardGame.winningPlayer.get() + " has informed player " + playerNumber + " that player " + CardGame.winningPlayer.get() + " has won");
        }

        logger.writeToFile("player", playerNumber, "player " + playerNumber + " exits");
        logger.writeToFile("player", playerNumber, "player " + playerNumber + " final hand: " + hand.getStringOfCardValues());
    }

    /**
     * Draws a card from the player's deck.
     */
    public synchronized void drawCard() {
        Card c = deck.pop();
        logger.writeToFile("player", playerNumber, ("player " + playerNumber + " draws a " + c.getValue() + " from deck " + playerNumber));
        hand.addCard(c);
    }

    /**
     * Discards the given card from the player's hand and puts it at the bottom of the next player's deck.
     */
    public synchronized void discardCard() {
        Card c = hand.getDiscardingCard();
        CardGame.getNextPlayer(this).getDeck().addCard(c);
        logger.writeToFile("player",playerNumber,("player " + playerNumber + " discards a " + c.getValue() + " to deck " + CardGame.getNextPlayer(this).getPlayerNumber()));
        hand.removeCard(c);
    }

    /**
     * Checks if player has a winning hand (4 of the same card)
     * @return True if player has a winning hand.
     */
    public boolean hasWon() {
        return (hand.isWinningHand());
    }

    /**
     * Getter method for player hand.
     * @return CardDeck object containing player hand.
     */
    public CardHand getHand(){
        return hand;
    }

    /**
     * Getter method for logger.
     * @return Instance of GameLogger object.
     */
    public GameLogger getLogger(){
        return logger;
    }

    /**
     * Getter method for player deck.
     * @return CardDeck object containing player deck.
     */
    public CardDeck getDeck(){
        return deck;
    }

    /**
     * Getter method for player number.
     * @return The current player number.
     */
    public int getPlayerNumber() { return playerNumber; }
}
