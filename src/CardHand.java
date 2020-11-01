import java.util.ArrayList;
import java.util.Random;

public class CardHand extends CardDeck {

    /**
     * Constructs an instance of CardDeck with the given card values.
     * @param cardValues Array of integer card values to add to the CardDeck.
     */
    public CardHand(int[] cardValues) {
        for(int v:cardValues){
            cards.add(new Card(v));
        }
    }

    /**
     * Finds the most common card value in the CardDeck.
     * Returns -1 if all card values are different.
     * @return Most common face value.
     */

    /**
     * Finds the mode value in player hand as first return of result.
     * Also finds the index of that value.
     * Eg: Result[0] for mode value.
     * Eg: Result[1] for index of mode value.
     * @return Result of mode value and its index.
     */
    public int[] modeWithIndex() {
        int maxCount = 1, maxValue = -1, index = 0;
        int[] result = new int[2];
        for(Card a:cards) {
            int count = 0;
            for(Card b:cards) {
                if(a.getValue() == b.getValue()) {
                    count++;
                }
            }
            if(count > maxCount){
                maxCount = count;
                maxValue = a.getValue();
            }
        }
        result[0] = maxValue;
        for(int i=0; i < cards.size(); i++) {
            if (cards.get(i).getValue() == maxValue) {
                index = i;
            }
        }
        result[1] = index;
        return result;
    }

    /**
     * Checks if hand contains 4 of the same value.
     * @return Boolean.
     */
    public boolean isWinningHand() {
        for (Card c : cards) {
            if ((c.getValue() != cards.get(0).getValue()))
                return false;
        }
        return true;
    }

    /**
     * Returns random Card object from CardHand object.
     * @return Card.
     */
    public Card randomCard(){
        Random r = new Random();
        return cards.get(r.nextInt(cards.size()));
    }

    /**
     * Returns random Card object from CardHand object where Card value is not given value.
     * @param v Value of cards to ignore.
     * @return Card.
     */
    public Card randomCard(int v){
        ArrayList<Card> filteredCards = cards;
        filteredCards.removeIf(c -> c.getValue() == v);
        Random r = new Random();
        return filteredCards.get(r.nextInt(filteredCards.size()));

//        ArrayList<Card> filteredCards = new ArrayList<>();
//        for(Card c:cards) {
//            if(c.getValue() == v) {
//                filteredCards.add(c);
//            }
//        }
//        Random r = new Random();
//        return filteredCards.get(r.nextInt(filteredCards.size()));
    }
}
