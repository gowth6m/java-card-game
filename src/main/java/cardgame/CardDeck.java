package cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    protected List<Card> cards;

    /**
     * Default constructor.
     */
    public CardDeck() {}

    /**
     * Constructs an instance of CardDeck with the given card values.
     * @param cardValues Array of integer card values to add to the CardDeck.
     */
    public CardDeck(int[] cardValues) {
        cards = Collections.synchronizedList(new ArrayList<>());
        convertValues(cardValues);
    }

    /**
     * Turns the list of integers into cards.
     * @param cardValues list of integers read from the input text files.
     */
    public void convertValues(int [] cardValues){
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
    public synchronized String getStringOfCardValues() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Card> listOfCards = new ArrayList<>(cards);
        for (Card c: listOfCards) {
            sb.append(" ");
            sb.append(c.getValue());
        }
        return sb.toString().stripLeading();
    }

    // Getter and Setter
    /**
     *
     * @return cards in CardDeck.
     */
    public List<Card> getCards(){ return cards; }
}
