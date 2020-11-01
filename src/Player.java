import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

public class Player implements Runnable{
    private int playerNumber;
    private volatile CardHand hand;
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
            } catch (ConcurrentModificationException e) { }
            if(this.hasWon()){
                System.out.println("Player "+ this.getPlayerNumber() + " has won.");
                CardGame.gameOver = true;
            } else {
                System.out.println("Draw card of " + "Player "+ this.getPlayerNumber());
                this.drawCard();
                System.out.println("Discard card of " + "Player "+ this.getPlayerNumber());
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
        this.hand.removeCard(c);
    }

    // TODO
    /**
     * Checks if player has a winning hand (4 of the same card)
     * @return True if player has a winning hand.
     */
    public boolean hasWon() {
        return (hand.isWinningHand() && hand.getCards().size() == 4);
//        return hand.isWinningHand();
    }

    /**
     * Gets the discarding card by checking mode.
     * @return The card the player should be discarding.
     */
    public Card getDiscardingCard() {
        int prefValue = hand.modeWithIndex()[0];
        Card c;
        Random r = new Random();
        if(prefValue == -1){
            c = this.getHand().cards.get(r.nextInt(this.getHand().cards.size()));
        } else {
            int index = 0;
            while(true){
                index = r.nextInt(this.getHand().cards.size());
                if(index != hand.modeWithIndex()[1]) {
                    break;
                }
            }
            c = this.getHand().cards.get(index);
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
