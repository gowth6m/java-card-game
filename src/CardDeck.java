import java.util.ArrayList;

public class CardDeck {

    public static class CardValueNotInDeckException extends Exception {

        public CardValueNotInDeckException(){ }

        public CardValueNotInDeckException(String m){
            super(m);
        }
    }

    protected volatile ArrayList<Card> cards = new ArrayList<>();

    /**
     * Default constructor.
     */
    public CardDeck() {}

    /**
     * Constructs an instance of CardDeck with the given card values.
     * @param cardValues Array of integer card values to add to the CardDeck.
     */
    public CardDeck(int[] cardValues) {
        for(int v:cardValues){
            cards.add(new Card(v));
        }
    }

    /**
     * Adds a card to a CardDeck.
     * @param c Card object to add to deck/hand.
     */
    public void addCard(Card c){
        this.cards.add(c);
    }

    /**
     * Removes a card from a CardDeck.
     * @param c Card object to remove.
     */
    public void removeCard(Card c){
        this.cards.remove(c);
    }

    /**
     * Removes a card from the top of the deck.
     * @return Removed Card object.
     */
    public Card pop() {
        return cards.remove(0);
    }

    /**
     * Adds a card to the bottom of the deck.
     * @param c Card to add to bottom of deck.
     */
    public void push(Card c){
        cards.add(c);
    }

    /**
     * Checks if CardDeck object contains any cards.
     * @return If CardDeck is empty.
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Gives the card values of the deck.
     * @return ArrayList of card values of the deck
     */
    public ArrayList<Integer> getListOfCardValues() {
        ArrayList<Integer> listOfValues = new ArrayList<>();
        for (Card c:cards) {
            listOfValues.add(c.getValue());
        }
        return listOfValues;
    }

    // Getter and Setter
    /**
     * Getter method to return the cardDeck.
     * @return The card deck
     */
    public ArrayList<Card> getCards(){
        return cards;
    }
}
