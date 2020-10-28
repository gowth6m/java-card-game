import java.util.ArrayList;

public class CardDeck {

    public static class CardValueNotInDeckException extends Exception {

        public CardValueNotInDeckException(){ }

        public CardValueNotInDeckException(String m){
            super(m);
        }
    }

    private ArrayList<Card> cards = new ArrayList<>();

    /**
     * Constructs an instance of CardDeck with the given card values.
     * @param cardValues Array of integer card values to add to the CardDeck.
     */
    public CardDeck(int[] cardValues) {
        for(int v:cardValues){
            cards.add(new Card(v));
        }
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    /**
     * Adds a card to a CardDeck.
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
     * Finds the most common card value in the CardDeck.
     * Will return -1 if no card value is found more than once.
     * @return Most common face value.
     */
    public int mode(){
        int maxCount = 1, maxValue = -1;
        for(Card c1:cards){
            int count = 0;
            for(Card c2:cards){
                if(c1.getValue() == c2.getValue()){
                    count++;
                }
            }
            if(count > maxCount){
                maxCount = count;
                maxValue = c1.getValue();
            }
        }
        if(maxValue == -1) {
            return cards.get(0).getValue();
        } else {
            return maxValue;
        }
    }

}
