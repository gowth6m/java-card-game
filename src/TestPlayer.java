import org.junit.*;

public class TestPlayer {
    private int noOfPlayers = 2;

    @Before
    public void setUp() {
        // player 1 -> hand: 1, 1, 1, 4  deck: 3, 1, 4, 3
        // player 2 -> hand: 2, 1, 3, 2  deck: 1, 4, 4, 2
        int[] winnigNumbers = {1,2,1,1,1,3,4,2,3,1,1,4,4,4,3,2};
        int[][][] dealtCards = Dealer.deal(winnigNumbers , noOfPlayers);
        for(int p = 0; p < noOfPlayers; p++) {
            CardGame.listOfPlayers.add(new Player(new CardHand(dealtCards[0][p]), new CardDeck(dealtCards[1][p]), p + 1));
        }
    }

    @Test
    public void testDiscardCard() {
        Assert.assertEquals("1 1 1 4", CardGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        CardGame.listOfPlayers.get(0).discardCard();
        Assert.assertEquals("1 1 1", CardGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
    }

    @Test
    public void testDrawCard() {
        Assert.assertEquals("1 1 1", CardGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        Assert.assertEquals("3 1 4 3", CardGame.listOfPlayers.get(0).getDeck().getStringOfCardValues());
        CardGame.listOfPlayers.get(0).drawCard();
        Assert.assertEquals("1 1 1 3", CardGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        Assert.assertEquals("1 4 3", CardGame.listOfPlayers.get(0).getDeck().getStringOfCardValues());
    }

}
