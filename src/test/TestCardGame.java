package test;

import main.CardGame;
import main.GameLogger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.net.URISyntaxException;

public class TestCardGame {
    private CardGame testGame = new CardGame();

    @Before
    public void setUp() {
        GameLogger.logging = false;
        GameLogger.printing = false;
        CardGame.numberOfPlayers = 2;
        CardGame.inputPackNumbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    }

    @Test
    public void testAskForNumberOfPlayers() throws URISyntaxException {
        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
        System.setIn(in);
        testGame.askForNumberOfPlayers();
        System.setIn(System.in);
        Assert.assertEquals(4, CardGame.numberOfPlayers);
    }

    @Test
    public void testInitialSetUp() {
        // Checks if cards gets dealt to players as expected.
        testGame.initialSetUp();
        Assert.assertEquals(2, CardGame.listOfPlayers.size());
        Assert.assertEquals("1 3 5 7", CardGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
        Assert.assertEquals("2 4 6 8", CardGame.listOfPlayers.get(1).getHand().getStringOfCardValues());
        Assert.assertEquals("9 11 13 15", CardGame.listOfPlayers.get(0).getDeck().getStringOfCardValues());
        Assert.assertEquals("10 12 14 16", CardGame.listOfPlayers.get(1).getDeck().getStringOfCardValues());
    }

    @Test
    public void testGetNextPlayer() {
        testGame.initialSetUp();
        Assert.assertEquals(2, CardGame.getNextPlayer(CardGame.listOfPlayers.get(0)).getPlayerNumber());
        Assert.assertEquals(1, CardGame.getNextPlayer(CardGame.listOfPlayers.get(1)).getPlayerNumber());
        CardGame.listOfPlayers.clear();
    }
}