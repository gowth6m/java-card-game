import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

public class Player implements Runnable{
    private final int playerNumber;
    private volatile CardHand hand;
    private volatile CardDeck deck;
    private final GameLogger logger = new GameLogger();

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
        while(!this.deck.isEmpty() && !CardGame.gameOver){
            // Logging to text file for testing
            ArrayList<Integer> handList = new ArrayList<>();
            ArrayList<Integer> deckList = new ArrayList<>();
            try {
                for(Card c: hand.getCards()) {
                    handList.add(c.getValue());
                }
                logger.writeToFile(this.getPlayerNumber(),"Player hand: " + handList);

                for(Card c: deck.getCards()) {
                    deckList.add(c.getValue());
                }
                logger.writeToFile(this.getPlayerNumber(),"Player deck: " + deckList);
            } catch (ConcurrentModificationException ignored) { }
            // Thread loop
            if(this.hasWon()){
                System.out.println("Player "+ this.getPlayerNumber() + " has won.");
                CardGame.gameOver = true;
            } else {
//                System.out.println("Draw card of " + "Player "+ this.getPlayerNumber());
                this.drawCard();
//                System.out.println("Discard card of " + "Player "+ this.getPlayerNumber());
                discardCard(this.getDiscardingCard());
            }
        }
    }

    /**
     * Draws a card from the player's deck.
     */
    public synchronized void drawCard() {
        logger.writeToFile(this.getPlayerNumber(),("Drew a card of " + this.deck.getCards().get(0).getValue()));
        this.hand.addCard(this.deck.pop());
    }

    /**
     * Discards the given card from the player's hand and puts it at the bottom of the next player's deck.
     */
    public synchronized void discardCard(Card c) {
        CardGame.getNextPlayer(this).getDeck().addCard(c);
        logger.writeToFile(this.getPlayerNumber(),("Discarded " + c.getValue()));
        logger.writeToFile(this.getPlayerNumber(),("Mode " + this.getHand().mode()));
        this.hand.removeCard(c);
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
    public synchronized Card getDiscardingCard() {
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
