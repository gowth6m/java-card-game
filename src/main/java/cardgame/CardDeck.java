package cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {

    protected List<Card> cards;

    /**
     * Default class constructor.
     */
    public CardDeck() {
    }

    /**
     * Class constructor specifying list of card values to be added to the CardDeck list.
     *
     * @param cardValues array of integer Card values to add to the CardDeck list
     */
    public CardDeck(int[] cardValues) {
        cards = Collections.synchronizedList(new ArrayList<>());
        convertValues(cardValues);
    }

    /**
     * Turns the list of integers into Card objects.
     *
     * @param cardValues list of integers read from the input text files
     */
    public void convertValues(int[] cardValues) {
        for (int v : cardValues) {
            cards.add(new Card(v));
        }
    }

    /**
     * Adds a Card to the 'bottom' of a CardDeck.
     *
     * @param c Card object to add to CardDeck
     */
    public void addCard(Card c) {
        cards.add(c);
    }

    /**
     * Removes a Card from a CardDeck.
     *
     * @param c Card object to remove from CardDeck
     */
    public void removeCard(Card c) {
        cards.remove(c);
    }

    /**
     * Removes a Card from the 'top' of the CardDeck.
     *
     * @return removed Card object
     */
    public Card pop() {
        return cards.remove(0);
    }

    /**
     * Checks if CardDeck object contains any cards.
     *
     * @return true if CardDeck is empty
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Gives a string of values of cards contained in a CardDeck separated by spaces.
     *
     * @return string containing values of Cards in CardDeck
     */
    public synchronized String getStringOfCardValues() {
        StringBuilder sb = new StringBuilder();
        ArrayList<Card> listOfCards = new ArrayList<>(cards);
        for (Card c : listOfCards) {
            sb.append(" ");
            sb.append(c.getValue());
        }
        return sb.toString().stripLeading();
    }

    /**
     * @return cards in CardDeck.
     */
    public List<Card> getCards() {
        return cards;
    }
}
