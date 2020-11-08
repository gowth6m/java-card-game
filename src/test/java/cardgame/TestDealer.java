package cardgame;

import org.junit.*;

public class TestDealer {

    @Before
    public void setUp() {
        GameLogger.logging = false;
        GameLogger.printing = false;
    }

    @Test
    @Deprecated
    public void testDeal() {
        // Checks if the expected output is an array containing an array of array of ints
        // Format of expected output: [ [[playerHand 1],[playerHand 2]] , [[playerDeck 1],[playerDeck 2]] ]
        final int numberOfPlayers = 2;
        final int[] inputPackNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[][][] expectedOutput = {{{1, 3, 5, 7}, {2, 4, 6, 8}}, {{9, 11, 13, 15}, {10, 12, 14, 16}}};
        Assert.assertEquals(expectedOutput, Dealer.deal(inputPackNumbers, numberOfPlayers));
    }
}
