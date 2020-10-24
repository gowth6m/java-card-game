import java.util.ArrayList;

public class CardDeck {
    private ArrayList<Card> cards = new ArrayList<>();

    public CardDeck(int[] cardValues) {
        for(int v:cardValues){
            cards.add(new Card(v));
        }
    }

    public void addCard(Card c){
        cards.add(c);
    }

    public Card removeCard(Card c){
        cards.remove(c);
        return c;
    }

}
