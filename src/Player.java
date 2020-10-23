public class Player implements Runnable {

    private Card[] currentHand = new Card[4];
    private CardDeck deck;
    private boolean isPlaying = false;

    public Player() { }

    /**
     *
     * @return - player draws a card from the deck
     */
    public void drawCard() {
    }

    /**
     *
     * @param card - the card that the player wants to remove
     */
    public void discardCard(Card card) {
    }

    /**
     *
     * @param currentHand - cards that player currently holds (max: 4)
     */
    public void setCurrentHand(Card[] currentHand) { this.currentHand = currentHand; }

    /**
     *
     * @param deck - this is the set that player picks cards from
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

    // THREAD TESTING FUNCTIONS
    public void testThread() {
        for (int i = 0; i < 20; i++ ) {
            System.out.println("Running thread: " + this.toString());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) { }

        }
    }

    public synchronized void syncTestThread() {
        for (int i = 0; i < 10; i++ ) {
            System.out.println("Sync test --------------------- " + this.toString());
        }
    }

    @Override
    public void run() {
        syncTestThread();
        testThread();
//        syncTestThread();
    }
}
