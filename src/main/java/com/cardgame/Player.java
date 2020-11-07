package com.cardgame;

public class Player implements Runnable{
    private final int playerNumber;
    private final CardHand hand;
    private final CardDeck deck;
    private final GameLogger logger;
    private final CardGame game;

    /**
     * Constructs an instance of player with their initial hand and deck values.
     * @param h CardDeck representing initial player hand.
     * @param d CardDeck representing initial player deck.
     * @param num Integer representing the player number.
     */
    public Player(CardGame g, CardHand h, CardDeck d, int num) {
        this.hand = h;
        this.deck = d;
        game = g;
        playerNumber = num;
        logger = new GameLogger();
        logger.writeToFile("player", playerNumber, "player " + playerNumber + " initial hand " + hand.getStringOfCardValues());
    }

    /**
     * Loop of the actual game for a player.
     * (check if deck is empty and if game is over) -> Draws card -> Discards card
     */
    public void run(){
        while(game.winningPlayer.get() == 0){
            if(hand.isWinningHand()){
                game.winningPlayer.set(playerNumber);
                if (GameLogger.printing) {
                    System.out.println("player " + playerNumber + " wins");
                }
            } else if (deck.isEmpty()){
                synchronized (this){
                    try {
                        wait();
                    } catch (InterruptedException ignored) {}
                }
            } else {
                drawCard();
                discardCard();
                synchronized (game.getNextPlayer(this)) {
                    game.getNextPlayer(this).notify();
                }
                logger.writeToFile("player", playerNumber,"player " + playerNumber + " current hand is " + hand.getStringOfCardValues());
                // TODO: REMOVE THIS BEFORE SUBMIT
                logger.writeToFile("player", playerNumber, "---------------------------------");
            }
        }
        synchronized (game.getNextPlayer(this)) {
            game.getNextPlayer(this).notify();
        }
        // logs the final deck in a separate deck text file and the final part of the log file for player.
        logger.writeToFile("deck", playerNumber,"deck" + playerNumber + " contents: " + deck.getStringOfCardValues());
        if(game.winningPlayer.get() == playerNumber){
            logger.writeToFile("player", playerNumber,"player " + playerNumber + " wins");
        } else {
            logger.writeToFile("player", playerNumber, "player " + game.winningPlayer.get() + " has informed player " + playerNumber + " that player " + game.winningPlayer.get() + " has won");
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
        game.getNextPlayer(this).getDeck().addCard(c);
//        game.getNextPlayingPlayer(this).getDeck().addCard(c);
        logger.writeToFile("player",playerNumber,("player " + playerNumber + " discards a " + c.getValue() + " to deck " + game.getNextPlayer(this).getPlayerNumber()));
        hand.removeCard(c);
    }

    /**
     * Getter method for player hand.
     * @return CardDeck object containing player hand.
     */
    public CardHand getHand(){
        return hand;
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
    public int getPlayerNumber() {
        return playerNumber;
    }
}
