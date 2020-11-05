package test;

import main.Dealer;
import org.junit.*;

public class TestDealer {
    private int numberOfPlayers = 2;
    private int[] inputPackNumbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};

    @Test
    @Deprecated
    public void testDeal(){
        int[][][] expectedOutput = {{{1,3,5,7},{2,4,6,8}} ,{{9,11,13,15},{10,12,14,16}}};
        Assert.assertEquals(expectedOutput, Dealer.deal(this.inputPackNumbers,this.numberOfPlayers));
    }
}
