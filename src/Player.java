import java.util.ArrayList;

public class Player {

    private Card[] currentHand = new Card[4];
    private CardDeck deck;
    private boolean isPlaying = false;

    public Player(CardDeck d) {
        deck = d;
    }

    /**
     *
     * @return - player draws a card from the deck
     */
    public Card drawCard() {
        Card card = new Card(2);
        return card;
    }

    /**
     *
     * @param currentHand
     */
    public void setCurrentHand(Card[] currentHand) {
        this.currentHand = currentHand;
    }

    /**
     *
     * @return - cards in player's hand
     */
    public Card[] getCurrentHand() {
        return currentHand;
    }

    /**
     *
     * @return - a deck from the pack that player can drawn from
     */
    public CardDeck getDeck() {
        return deck;
    }

    /**
     *
     * @param c
     */
    public void pickUp(Card c){

    }
}
