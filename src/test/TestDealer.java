package test;

import main.Dealer;
import main.GameLogger;
import org.junit.*;

public class TestDealer {
    private int numberOfPlayers = 2;
    private int[] inputPackNumbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};

    @Before
    public void setUp() {
        GameLogger.logging = false;
        GameLogger.printing = false;
    }

    @Test
    @Deprecated
    public void testDeal(){
        // Checks if the expected output is an array containing an array of array of ints
        // Format of expected output: [ [[playerHand 1],[playerHand 2]] , [[playerDeck 1],[playerDeck 2]] ]
        int[][][] expectedOutput = {{{1,3,5,7},{2,4,6,8}} ,{{9,11,13,15},{10,12,14,16}}};
        Assert.assertEquals(expectedOutput, Dealer.deal(this.inputPackNumbers,this.numberOfPlayers));
    }
}
