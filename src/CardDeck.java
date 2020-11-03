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
     * Adds a card of the given value to a CardDeck.
     * @param v Face value of the card to add.
     */
    public void addCardByValue(int v){
        cards.add(new Card(v));
    }

    /**
     * Removes a card of the given value from a CardDeck and returns the card object.
     * Throws CardValueNotInDeckException if card cannot be found.
     * @param v Face value of card to remove.
     * @return Removed Card object .
     */
    public Card removeCardByValue(int v) throws CardValueNotInDeckException {
        for(Card c:cards){
            if (c.getValue() == v) {
                cards.remove(c);
                return c;
            }
        }
        throw new CardValueNotInDeckException();
    }

    /**
     * Gives the card values of the deck.
     * @return ArrayList of card values of the deck
     */
    public ArrayList<Integer> getListOfCardValues() {
        ArrayList<Integer> listOfValues = new ArrayList<>();
        for (Card c:this.cards) {
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
