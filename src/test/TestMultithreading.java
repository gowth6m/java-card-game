package test;

import main.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMultithreading {
    private long timeLimit = System.currentTimeMillis() + 5000;
    private final CardGame testGame = new CardGame();

    /**
     * Takes list of card numbers, assigns starting deck/hand for players and starts thread for each player hence starting the game.
     * @param pack The list of numbers for the cards in CardDeck/Hand.
     */
    private synchronized void dealForTestRun(int[] pack) {
        int numberOfPlayers = pack.length / 8;
        int[][][] dealtCards = Dealer.deal(pack, numberOfPlayers);
        for (int p = 0; p < numberOfPlayers; p++) {
            testGame.listOfPlayers.add(new Player(testGame, new CardHand(dealtCards[0][p]), new CardDeck(dealtCards[1][p]), p + 1));
        }
        for(Player p:testGame.listOfPlayers){
            (new Thread(p)).start();
        }
    }

    @Before
    public void setUp() {
        GameLogger.logging = false;
        GameLogger.printing = false;
        testGame.listOfPlayers.clear();
        testGame.winningPlayer.set(0);
    }

    @After
    public void tearDown() {
        testGame.listOfPlayers.clear();
        testGame.winningPlayer.set(0);
    }

    /**
     * TEST CASE 1: 2 player game
     * Player 1 should win here as player 1 is assigned a hand of [1,1,1,3] at the start amd the only possible way of
     * winning in this pack is by getting four 1s.
     */
    @Test
    public void testGameOne() {
        int[] pack = {1,2,1,2,1,1,3,3,4,4,5,5,4,5,6,6};
        dealForTestRun(pack);
        while(true) {
            if(testGame.winningPlayer.get() != 0) {
                Assert.assertEquals("1 1 1 1", testGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
                break;
            }
        }
    }

    /**
     * TEST CASE 2: 8 player game
     * Player 1 should win here as player 1 is assigned a hand of [1,1,1,1] first.
     * This is to test which player wins even though they all begin with winning hands.
     */
    @Test
    public void testGameTwo() {
        int[] pack = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
        dealForTestRun(pack);
        while(true) {
            if (testGame.winningPlayer.get() != 0) {
                Assert.assertEquals(1, testGame.winningPlayer.get());
                Assert.assertNotEquals(2, testGame.winningPlayer.get());
                Assert.assertEquals("1 1 1 1", testGame.listOfPlayers.get(0).getHand().getStringOfCardValues());
                Assert.assertEquals("2 2 2 2", testGame.listOfPlayers.get(1).getHand().getStringOfCardValues());
                Assert.assertEquals("3 3 3 3", testGame.listOfPlayers.get(2).getHand().getStringOfCardValues());
                Assert.assertEquals("4 4 4 4", testGame.listOfPlayers.get(3).getHand().getStringOfCardValues());
                Assert.assertEquals("5 5 5 5", testGame.listOfPlayers.get(4).getHand().getStringOfCardValues());
                Assert.assertEquals("6 6 6 6", testGame.listOfPlayers.get(5).getHand().getStringOfCardValues());
                Assert.assertEquals("7 7 7 7", testGame.listOfPlayers.get(6).getHand().getStringOfCardValues());
                Assert.assertEquals("8 8 8 8", testGame.listOfPlayers.get(7).getHand().getStringOfCardValues());
                break;
            }
        }
    }

    /**
     * TEST CASE 3: 8 player game
     * Player 2 should win here as player 2 is assigned a hand of [2,2,2,29] at the start.
     * Only possibility of winning is by getting four 2s.
     * Player 2's deck at the start: [12,12,12,22]
     */
    @Test
    public void testGameThree() {
        int[] pack = {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 11, 29, 13, 14, 15, 16, 29, 18,
                11, 12, 13, 14, 15, 16, 17, 18, 11, 12, 13, 14, 15, 16, 17, 18, 11, 12, 13, 14, 15, 16, 17, 18, 21, 22, 23, 2, 25, 26, 27, 28};
        dealForTestRun(pack);
        while(true) {
            if(testGame.winningPlayer.get() != 0) {
                Assert.assertEquals(2, testGame.winningPlayer.get());
                Assert.assertEquals("2 2 2 2", testGame.listOfPlayers.get(1).getHand().getStringOfCardValues());
                break;
            }
        }
    }

    /**
     * TEST CASE 4: 4 player game
     * Player 3 should win the game as player 3 is assigned a hand of [3,11,3,7] at the start.
     * Only possibility of winning is by getting four 3s.
     * Player 3's deck at start: [3,7,12,16]
     */
    @Test
    public void testGameFour() {
        int[] pack = {1,2,3,4, 5,6,11,8, 1,2,3,4, 5,6,7,8, 1,2,13,4, 5,6,7,8, 10,11,3,14, 14,3,16,17};
        dealForTestRun(pack);
        while (true) {
            if (testGame.winningPlayer.get() != 0) {
                Assert.assertEquals(3, testGame.winningPlayer.get());
                Assert.assertEquals("3 3 3 3", testGame.listOfPlayers.get(2).getHand().getStringOfCardValues());
                break;
            }
        }
    }

    /**
     * TEST CASE 5: 10 player game
     * Player 9 should win the game as player 9 is assigned a hand of [9,9,19,29] at the start.
     * Only possibility of winning is by getting four 9s.
     * Player 3's deck at start: [39,49,59,69]
     */
    @Test
    public void testGameFive() {
        int[] pack = {
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                    11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                    21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                    31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                    41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                    51, 52, 53, 54, 55, 56, 57, 9, 59, 60,
                    61, 62, 63, 64, 65, 66, 9, 68, 69, 70};
        dealForTestRun(pack);
        while (true) {
            if (testGame.winningPlayer.get() != 0) {
                Assert.assertEquals(9, testGame.winningPlayer.get());
                Assert.assertEquals("9 9 9 9", testGame.listOfPlayers.get(8).getHand().getStringOfCardValues());
                Assert.assertEquals("9 9 9 9", testGame.listOfPlayers.get(8).getHand().getStringOfCardValues());
                break;
            }
        }
    }
}
