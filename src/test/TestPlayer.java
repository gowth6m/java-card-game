package test;

import main.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPlayer {
    private final CardGame testGame = new CardGame();

    /**
     * Takes list of card numbers, assigns starting deck/hand for players.
     * @param pack The list of numbers for the cards in CardDeck/Hand.
     */
    public void dealForTests(int[] pack) {
        int numberOfPlayers = pack.length / 8;
        int[][][] dealtCards = Dealer.deal(pack, numberOfPlayers);
        for (int p = 0; p < numberOfPlayers; p++) {
            testGame.listOfPlayers.add(new Player(testGame, new CardHand(dealtCards[0][p]), new CardDeck(dealtCards[1][p]), p + 1));
        }
    }

    /**
     * Takes list of card numbers, uses dealForTests to deal cards out.
     * @param pack The list of numbers for the cards in CardDeck/Hand.
     */
    private void dealForTestRun(int[] pack) {
        dealForTests(pack);
    }

    @Before
    public void setUp() {
        GameLogger.logging = false;
        GameLogger.printing = false;
    }

    @Test
    public void testDiscardCard() {
        // Discarding card has to be non-preferred card so it can only be 4.
        testGame.listOfPlayers.clear();
        int[] winningNumbers = {1,2,1,1,1,3,4,2,3,1,1,4,4,4,3,2};
        dealForTestRun(winningNumbers);
        Assert.assertEquals("1 1 1 4", testGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        testGame.listOfPlayers.get(0).discardCard();
        Assert.assertEquals("1 1 1", testGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        // Checks the other players deck if its in there.
        Assert.assertEquals("1 4 4 2 4", testGame.listOfPlayers.get(1).getDeck().getStringOfCardValues());
    }

    @Test
    public void testDrawCard() {
        testGame.listOfPlayers.clear();
        int[] winningNumbers = {1,2,1,1,1,3,4,2,3,1,1,4,4,4,3,2};
        dealForTestRun(winningNumbers);
        Assert.assertEquals("1 1 1 4", testGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        Assert.assertEquals("3 1 4 3", testGame.listOfPlayers.get(0).getDeck().getStringOfCardValues());
        testGame.listOfPlayers.get(0).drawCard();
        Assert.assertEquals("1 1 1 4 3", testGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        Assert.assertEquals("1 4 3", testGame.listOfPlayers.get(0).getDeck().getStringOfCardValues());
    }
}
