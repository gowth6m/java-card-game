package test;

import main.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMultithreading {

    /**
     * Takes list of card numbers, assigns starting deck/hand for players and starts thread for each player hence starting the game.
     * @param pack The list of numbers for the cards in CardDeck/Hand.
     */
    private synchronized void dealForTestRun(int[] pack) {
        TestPlayer.dealForTests(pack);
        for(Player p:CardGame.listOfPlayers){
            (new Thread(p)).start();
        }
    }

    /**
     * TEST CASE 1:
     * Player 1 should win here as player 1 is assigned a hand of [1,1,1,3] at the start amd the only possible way of
     * winning in this pack is by getting four 1s.
     */
    @Before
    public void setUp() {
        GameLogger.logging = false;
        GameLogger.printing = false;
        CardGame.winningPlayer.set(0);
    }

    @Test
    public void testGameOne() {
        CardGame.listOfPlayers.clear();
        int[] pack = {1,2,1,2,1,1,3,3,4,4,5,5,4,5,6,6};
        dealForTestRun(pack);
        while(true) {
            if(CardGame.winningPlayer.get() != 0) {
                Assert.assertEquals("1 1 1 1", CardGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
                break;
            }
        }
        CardGame.winningPlayer.set(0);
    }

    /**
     * TEST CASE 2:
     * Player 1 should win here as player 1 is assigned a hand of [1,1,1,1] first.
     * This is to test which player wins even though they all begin with winning hands.
     */
    @Test
    public void testGameTwo() {
        CardGame.listOfPlayers.clear();
        int[] pack = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
        dealForTestRun(pack);
        while(true) {
            if (CardGame.winningPlayer.get() != 0) {
                Assert.assertEquals(1, CardGame.winningPlayer.get());
                Assert.assertNotEquals(2, CardGame.winningPlayer.get());
                Assert.assertEquals("1 1 1 1", CardGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
                Assert.assertEquals("2 2 2 2", CardGame.listOfPlayers.get(1).getHand().getStringOfCardValues());
                Assert.assertEquals("3 3 3 3", CardGame.listOfPlayers.get(2).getHand().getStringOfCardValues());
                Assert.assertEquals("4 4 4 4", CardGame.listOfPlayers.get(3).getHand().getStringOfCardValues());
                Assert.assertEquals("5 5 5 5", CardGame.listOfPlayers.get(4).getHand().getStringOfCardValues());
                Assert.assertEquals("6 6 6 6", CardGame.listOfPlayers.get(5).getHand().getStringOfCardValues());
                Assert.assertEquals("7 7 7 7", CardGame.listOfPlayers.get(6).getHand().getStringOfCardValues());
                Assert.assertEquals("8 8 8 8", CardGame.listOfPlayers.get(7).getHand().getStringOfCardValues());
                break;
            }
        }
        CardGame.winningPlayer.set(0);
    }

    /**
     * TEST CASE 3:
     * Player 2 should win here as player 2 is assigned a hand of [2,2,2,29] at the start.
     * This is just to test the game with 8 players.
     */
    @Test
    public void testGameThree() {
        CardGame.listOfPlayers.clear();
        int[] pack = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 11, 29, 13, 14, 15, 16, 29, 18,
                11, 12, 13, 14, 15, 16, 17, 18, 11, 12, 13, 14, 15, 16, 17, 18, 11, 12, 13, 14, 15, 16, 17, 18, 21, 22, 23, 2, 25, 26, 27, 28};
        dealForTestRun(pack);
        while(true) {
            if(CardGame.winningPlayer.get() != 0) {
                Assert.assertEquals(2, CardGame.winningPlayer.get());
                Assert.assertEquals("2 2 2 2", CardGame.listOfPlayers.get(1).getHand().getStringOfCardValues());
                break;
            }
        }
        CardGame.winningPlayer.set(0);
    }
}
