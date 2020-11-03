import java.util.ArrayList;

public class CardDeck {

    protected final ArrayList<Card> cards = new ArrayList<>();

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
     * Adds a card to the 'bottom' of a CardDeck.
     * @param c Card object to add to deck/hand.
     */
    public void addCard(Card c){
        cards.add(c);
    }

    /**
     * Removes a card from a CardDeck.
     * @param c Card object to remove.
     */
    public void removeCard(Card c){
        cards.remove(c);
    }

    /**
     * Removes a card from the top of the deck.
     * @return Removed Card object.
     */
    public Card pop() {
        return cards.remove(0);
    }

    /**
     * Checks if CardDeck object contains any cards.
     * @return If CardDeck is empty.
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Gives a string of values of cards contained in a CardDeck separated by spaces.
     * @return String containing values of cards.
     */
    public String getStringOfCardValues(){
        StringBuilder sb = new StringBuilder();
        for (Card c:cards) {
            sb.append(" ");
            sb.append(c.getValue());
        }
        return sb.toString();
    }
}
