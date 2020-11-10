package cardgame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMultithreading {

    @Before
    public void setUp() {
        GameLogger.logging = false;
        GameLogger.printing = false;
    }

    /**
     * TEST CASE 1: 2 player game
     * Player 1 should win here as player 1 is assigned a hand of [1,1,1,3] at the start amd the only possible way of
     * winning in this pack is by getting four 1s.
     */
    @Test
    public void testGameOne() {
        int[] pack = {1, 2, 1, 2, 1, 1, 3, 3, 4, 4, 5, 5, 4, 5, 6, 6};
        final CardGame testGame = new CardGame(2, pack);
        testGame.initialSetUp();
        testGame.startGame();
        while (true) {
            if (testGame.winningPlayer.get() != 0) {
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
        final CardGame testGame2 = new CardGame(8, pack);
        testGame2.initialSetUp();
        testGame2.startGame();
        while (true) {
            if (testGame2.winningPlayer.get() != 0) {
                Assert.assertEquals(1, testGame2.winningPlayer.get());
                Assert.assertNotEquals(2, testGame2.winningPlayer.get());
                Assert.assertEquals("1 1 1 1", testGame2.listOfPlayers.get(0).getHand().getStringOfCardValues());
                Assert.assertEquals("2 2 2 2", testGame2.listOfPlayers.get(1).getHand().getStringOfCardValues());
                Assert.assertEquals("3 3 3 3", testGame2.listOfPlayers.get(2).getHand().getStringOfCardValues());
                Assert.assertEquals("4 4 4 4", testGame2.listOfPlayers.get(3).getHand().getStringOfCardValues());
                Assert.assertEquals("5 5 5 5", testGame2.listOfPlayers.get(4).getHand().getStringOfCardValues());
                Assert.assertEquals("6 6 6 6", testGame2.listOfPlayers.get(5).getHand().getStringOfCardValues());
                Assert.assertEquals("7 7 7 7", testGame2.listOfPlayers.get(6).getHand().getStringOfCardValues());
                Assert.assertEquals("8 8 8 8", testGame2.listOfPlayers.get(7).getHand().getStringOfCardValues());
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
        final CardGame testGame3 = new CardGame(8, pack);
        testGame3.initialSetUp();
        testGame3.startGame();
        while (true) {
            if (testGame3.winningPlayer.get() != 0) {
                Assert.assertEquals(2, testGame3.winningPlayer.get());
                Assert.assertEquals("2 2 2 2", testGame3.listOfPlayers.get(1).getHand().getStringOfCardValues());
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
        int[] pack = {1, 2, 3, 4, 5, 6, 11, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 13, 4, 5, 6, 7, 8, 10, 11, 3, 14, 14, 3, 16, 17};
        final CardGame testGame4 = new CardGame(4, pack);
        testGame4.initialSetUp();
        testGame4.startGame();
        while (true) {
            if (testGame4.winningPlayer.get() != 0) {
                Assert.assertEquals(3, testGame4.winningPlayer.get());
                Assert.assertEquals("3 3 3 3", testGame4.listOfPlayers.get(2).getHand().getStringOfCardValues());
                break;
            }
        }
    }

    /**
     * TEST CASE 5: 10 player game
     * Player 9 should win the game as player 9 is assigned a hand of [9,9,19,29] at the start.
     * Only possibility of winning is by getting four 9s.
     * Player 3's deck at start: [39,9,59,69]
     */
    @Test
    public void testGameFive() {
        int[] pack = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                41, 42, 43, 44, 45, 46, 47, 48, 9, 50,
                51, 52, 53, 54, 55, 56, 57, 9, 59, 60,
                61, 62, 63, 64, 65, 66, 19, 68, 69, 70};
        final CardGame testGame5 = new CardGame(10, pack);
        testGame5.initialSetUp();
        testGame5.startGame();
        while (true) {
            if (testGame5.winningPlayer.get() != 0) {
                Assert.assertEquals(9, testGame5.winningPlayer.get());
                Assert.assertEquals("9 9 9 9", testGame5.listOfPlayers.get(8).getHand().getStringOfCardValues());
                Assert.assertEquals("9 9 9 9", testGame5.listOfPlayers.get(8).getHand().getStringOfCardValues());
                break;
            }
        }
    }
}
