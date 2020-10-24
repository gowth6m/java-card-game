public class Player {

    private CardDeck hand;
    private CardDeck deck;

    /**
     * Constructs an instance of player with their initial hand and deck values.
     * @param h CardDeck representing initial player hand.
     * @param d CardDeck representing initial player deck.
     */
    public Player(CardDeck h, CardDeck d) {
        this.hand = h;
        this.deck = d;
    }

    /**
     * Draws a card from the player's deck.
     */
    public void drawCard() {
        hand.addCard(deck.pop());
    }

    /**
     * Discards the given card from the player's hand and puts it at the bottom of the next player's deck.
     */
    // TODO
    public void discardCard() {

    }
}
