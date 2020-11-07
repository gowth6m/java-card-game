package test.java;

import main.java.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestPlayer {
    private final CardGame testGame = new CardGame(2, new int[]{1,2,1,1,1,3,4,2,3,1,1,4,4,4,3,2});

    @Before
    public void setUp() {
        GameLogger.logging = false;
        GameLogger.printing = false;
        testGame.initialSetUp();
        testGame.listOfPlayers.add(new Player(testGame, new CardHand(new int[]{1, 1, 1, 4}), new CardDeck(new int[]{3, 1, 4, 3}), 1));
        testGame.listOfPlayers.add(new Player(testGame, new CardHand(new int[]{2, 1, 3, 2}), new CardDeck(new int[]{1, 4, 4, 2}), 2));
    }

    @Test
    public void testDiscardCard() {
        // Discarding card has to be non-preferred card so it can only be 4.
        Assert.assertEquals("1 1 1 4", testGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        testGame.listOfPlayers.get(0).discardCard();
        Assert.assertEquals("1 1 1", testGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        // Checks the other players deck if its in there.
        Assert.assertEquals("1 4 4 2 4", testGame.listOfPlayers.get(1).getDeck().getStringOfCardValues());
    }

    @Test
    public void testDrawCard() {
        Assert.assertEquals("1 1 1 4", testGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        Assert.assertEquals("3 1 4 3", testGame.listOfPlayers.get(0).getDeck().getStringOfCardValues());
        testGame.listOfPlayers.get(0).drawCard();
        Assert.assertEquals("1 1 1 4 3", testGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        Assert.assertEquals("1 4 3", testGame.listOfPlayers.get(0).getDeck().getStringOfCardValues());
    }
}
