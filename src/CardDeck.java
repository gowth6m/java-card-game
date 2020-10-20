import java.util.ArrayList;

public class CardDeck {
    // the deck that player picks from
    private ArrayList<Card> cards;

    public CardDeck(int[] intList) {
        this.cards = Utilities.intArrToCardArrList(intList);
    }

    /**
     *
     * @return - an arrayList of cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }
}
