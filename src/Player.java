import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.Random;

public class Player implements Runnable{
    private final int playerNumber;
    private CardHand hand;
    private volatile CardDeck deck;
    private GameLogger logger = new GameLogger();

    /**
     * Constructs an instance of player with their initial hand and deck values.
     * @param h CardDeck representing initial player hand.
     * @param d CardDeck representing initial player deck.
     */
    public Player(CardHand h, CardDeck d) {
        this.hand = h;
        this.deck = d;
        playerNumber = CardGame.listOfPlayers.size() + 1;
    }

    /**
     * Loop of the actual game for a player.
     * (check if deck is empty and if game is over) -> Draws card -> Discards card
     */
    public void run(){
        while(CardGame.winningPlayer.get() == 0){
            // Thread loop
            if(hasWon()){
                CardGame.winningPlayer.set(playerNumber);
                System.out.println("player "+ playerNumber + " wins");
            } else if (deck.isEmpty()){
                synchronized (this){
                    try {
                        wait();
                    } catch (InterruptedException e) {}
                }
            } else {
                drawCard();
                discardCard(getDiscardingCard());
                synchronized (CardGame.getNextPlayer(this)){
                    CardGame.getNextPlayer(this).notify();
                }
                // Logging
                logger.writeToFile("player", playerNumber,"player " + playerNumber + " current hand is " + Utilities.formatCardValues(hand));
                // TODO REMOVE THIS BEFORE SUBMIT
                logger.writeToFile("player", playerNumber, "--------------------------------");
            }
        }
        // logs the final deck in a separate deck text file.
        logger.writeToFile("deck", playerNumber,"deck" + playerNumber + " contents: " + Utilities.formatCardValues(deck));

        if(CardGame.winningPlayer.get() == playerNumber){
            logger.writeToFile("player", playerNumber,"player " + playerNumber + " wins");
        } else {
            logger.writeToFile("player", playerNumber, "player " + CardGame.winningPlayer.get() + " has informed player " + playerNumber + " that player " + CardGame.winningPlayer.get() + " has won");
        }

        logger.writeToFile("player", playerNumber, "player " + playerNumber + " exits");
        logger.writeToFile("player", playerNumber, "player " + playerNumber + " final hand: " + Utilities.formatCardValues(hand));
    }

    /**
     * Draws a card from the player's deck.
     */
    public void drawCard() {
        Card c = deck.pop();
        logger.writeToFile("player", playerNumber, ("player " + playerNumber + " draws a " + c.getValue() + " from deck " + playerNumber));
        hand.addCard(c);
    }

    /**
     * Discards the given card from the player's hand and puts it at the bottom of the next player's deck.
     */
    public void discardCard(Card c) {
        CardGame.getNextPlayer(this).getDeck().addCard(c);
        logger.writeToFile("player",this.getPlayerNumber(),("player " + this.getPlayerNumber() + " discards a " + c.getValue() + " to deck " + CardGame.getNextPlayer(this).getPlayerNumber()));
        hand.removeCard(c);
    }

    /**
     * Checks if player has a winning hand (4 of the same card)
     * @return True if player has a winning hand.
     */
    public boolean hasWon() {
        return (hand.isWinningHand() && hand.getCards().size() == 4);
    }

    /**
     * Gets the discarding card by checking mode and making sure its not preferredValue card.
     * @return The card the player should be discarding.
     */
    public Card getDiscardingCard() {
        int prefValue = hand.mode();
        Card c;
        Random r = new Random();
        if(prefValue == -1){
            c = this.getHand().cards.get(r.nextInt(this.getHand().cards.size()));
        } else {
            // making a list of index's of the preferred values.
            int[] preferredValuesIndex = Utilities.intArrToIntList(this.getHand().listOfModeIndex());
            // gets an index that isn't in the list of preferred values at random.
            int excludedInt = Utilities.nextIntInRangeButExclude(0,this.getHand().cards.size(),preferredValuesIndex);
            c = this.getHand().cards.get(excludedInt);
        }
        return c;
    }

    // Getter & Setter
    /**
     * Getter method for player hand.
     * @return CardDeck object containing player hand.
     */
    public CardHand getHand(){
        return hand;
    }

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
    public int getPlayerNumber() {return playerNumber; }
}
