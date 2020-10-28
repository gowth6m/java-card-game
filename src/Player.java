public class Player implements Runnable{

    private volatile CardHand hand;
    private volatile CardDeck deck;

    /**
     * Constructs an instance of player with their initial hand and deck values.
     * @param h CardDeck representing initial player hand.
     * @param d CardDeck representing initial player deck.
     */
    public Player(CardHand h, CardDeck d) {
        this.hand = h;
        this.deck = d;
    }

    public void run(){
        while(!deck.isEmpty()){
            System.out.println("Starting run of " + this);
            if(this.hasWon()){
                System.out.println("Player has won.");
                CardGame.gameOver = true;
            } else {
                System.out.println("Draw card of " + this);
                drawCard();
                int prefValue = hand.mode();
                Card c;
                if(prefValue == -1){
                    c = hand.randomCard();
                } else {
                    c = hand.randomCard(prefValue);
                }
                System.out.println("Discard card of " + this);
                discardCard(CardGame.getNextPlayer(this), c);
            }

        }
    }

    /**
     * Getter method for player hand.
     * @return CardDeck object containing player hand.
     */
    public CardDeck getHand(){
        return hand;
    }

    /**
     * Getter method for player deck.
     * @return CardDeck object containing player deck.
     */
    public CardDeck getDeck(){
        return deck;
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
    public void discardCard(Player p, Card c) {
        p.getDeck().addCard(c);
        hand.removeCard(c);
    }

    public boolean hasWon() {
        return hand.isWinningHand();
    }

}
