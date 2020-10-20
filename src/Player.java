import java.util.ArrayList;

public class Player {

    private Card[] currentHand = new Card[4];
    private CardDeck deck;
    private boolean isPlaying = false;

    public Player() {

    }

    /**
     *
     * @return - player draws a card from the deck
     */
    public void drawCard() { }

    /**
     *
     * @param currentHand
     */
    public void setCurrentHand(Card[] currentHand) { this.currentHand = currentHand; }

    /**
     *
     * @param deck
     */
    public void setDeck(CardDeck deck) { this.deck = deck; }

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
    public CardDeck getDeck() { return deck; }

}