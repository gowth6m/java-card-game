import java.util.ArrayList;

public class CardDeck {
    private ArrayList<Card> cards;

    public CardDeck(int[] intList) {
        this.cards = Utilities.intArrToCardArrList(intList);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
