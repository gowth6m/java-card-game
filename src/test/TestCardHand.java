package test;

import main.Card;
import main.CardHand;
import org.junit.Assert;
import org.junit.Test;

public class TestCardHand {
    private CardHand testHandOne = new CardHand(new int[]{2, 2, 2, 2, 5});
    private CardHand testHandTwo = new CardHand(new int[]{2, 2, 3, 3});

    @Test
    public void testMode() {
        Assert.assertEquals(2, testHandOne.mode());
        Assert.assertEquals(2, testHandTwo.mode());
    }

    @Test
    public void testGetDiscardingCard() {
        Assert.assertEquals(5, testHandOne.getDiscardingCard().getValue());
        Assert.assertEquals(3, testHandTwo.getDiscardingCard().getValue());
    }

    @Test
    public void testIsWinningHand() {
        CardHand testWinningHand = new CardHand(new int[]{2, 2, 2, 2});
        CardHand testLoosingHandOne = new CardHand(new int[]{2, 2, 2});
        CardHand testLoosingHandTwo = new CardHand(new int[]{2, 2, 2, 2, 2});
        CardHand testLoosingHandThree = new CardHand(new int[]{2, 2, 2, 3});
        Assert.assertTrue("Expected true for hand: 2 2 2 2", testWinningHand.isWinningHand());
        Assert.assertFalse("Expected false for hand: 2 2 2", testLoosingHandOne.isWinningHand());
        Assert.assertFalse("Expected false for hand: 2 2 2 2 2", testLoosingHandTwo.isWinningHand());
        Assert.assertFalse("Expected false for hand: 2 2 2 3", testLoosingHandThree.isWinningHand());
    }

    @Test
    public void testRandomCard() {
        boolean isRandomNumberInHand = false;
        int randomCardNumber = testHandOne.randomCard().getValue();
        for(Card c:testHandOne.getCards()) {
            if(randomCardNumber == c.getValue()) {
                isRandomNumberInHand = true;
                break;
            }
        }
        Assert.assertTrue("Random card was not in hand", isRandomNumberInHand);
    }

    @Test
    public void testRandomCardWithException() {
        Assert.assertEquals(5, testHandOne.randomCard(2).getValue());
        Assert.assertEquals(2, testHandOne.randomCard(5).getValue());
    }
}
