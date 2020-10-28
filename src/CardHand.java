import java.util.ArrayList;
import java.util.Random;

public class CardHand extends CardDeck{

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
        return maxValue;
    }

    /**
     * Checks if hand contains 4 of the same value.
     * @return Boolean.
     */
    public boolean isWinningHand() {
        int v = cards.get(0).getValue();
        for(int i = 1; i < cards.size(); i++){
            if(cards.get(i).getValue() != v){
                return false;
            }
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
    }
}
