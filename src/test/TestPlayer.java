package test;

import main.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPlayer {

    /**
     * Takes list of card numbers, assigns starting deck/hand for players.
     * @param pack The list of numbers for the cards in CardDeck/Hand.
     */
    public static void dealForTests(int[] pack) {
        int numberOfPlayers = pack.length / 8;
        int[][][] dealtCards = Dealer.deal(pack, numberOfPlayers);
        for (int p = 0; p < numberOfPlayers; p++) {
            CardGame.listOfPlayers.add(new Player(new CardHand(dealtCards[0][p]), new CardDeck(dealtCards[1][p]), p + 1));
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
        CardGame.listOfPlayers.clear();
        int[] winningNumbers = {1,2,1,1,1,3,4,2,3,1,1,4,4,4,3,2};
        dealForTestRun(winningNumbers);
        Assert.assertEquals("1 1 1 4", CardGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        CardGame.listOfPlayers.get(0).discardCard();
        Assert.assertEquals("1 1 1", CardGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        // Checks the other players deck if its in there.
        Assert.assertEquals("1 4 4 2 4", CardGame.listOfPlayers.get(1).getDeck().getStringOfCardValues());
    }

    @Test
    public void testDrawCard() {
        CardGame.listOfPlayers.clear();
        int[] winningNumbers = {1,2,1,1,1,3,4,2,3,1,1,4,4,4,3,2};
        dealForTestRun(winningNumbers);
        Assert.assertEquals("1 1 1 4", CardGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        Assert.assertEquals("3 1 4 3", CardGame.listOfPlayers.get(0).getDeck().getStringOfCardValues());
        CardGame.listOfPlayers.get(0).drawCard();
        Assert.assertEquals("1 1 1 4 3", CardGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        Assert.assertEquals("1 4 3", CardGame.listOfPlayers.get(0).getDeck().getStringOfCardValues());
    }
}
