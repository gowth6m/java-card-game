package com.cardgame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCardHand {
    private final CardHand testHandOne = new CardHand(new int[]{2, 2, 2, 2, 5});
    private final CardHand testHandTwo = new CardHand(new int[]{2, 2, 3, 3});
    private final CardHand testHandThree = new CardHand(new int[]{1, 2, 3, 4, 5});
    private final CardHand testHandFour = new CardHand(new int[]{1, 2, 3, 3, 2});
    private final CardHand emptyHand = new CardHand(new int[]{});

    @Before
    public void setUp() {
        GameLogger.logging = false;
        GameLogger.printing = false;
    }

    @Test
    public void testMode() {
        // Hand: 2 2 2 2 5
        Assert.assertEquals(2, testHandOne.mode());
    }

    @Test
    public void testModeWithTwoPossibility() {
        // Hand: 2 2 3 3
        Assert.assertEquals(2, testHandTwo.mode());
        // Hand: 1 2 3 3 2
        Assert.assertEquals(2, testHandFour.mode());
    }

    @Test
    public void testModeWithNoMode() {
        // No mode so it should return -1
        // Hand: 1 2 3 4 5
        Assert.assertEquals(-1, testHandThree.mode());
        // Hand: empty
        Assert.assertEquals(-1, emptyHand.mode());
    }

    @Test
    public void testGetDiscardingCard() {
        // Discarding card should be one of the card that isn't the mode (preferred value)
        // Hand: 2 2 2 2 5
        Assert.assertEquals(5, testHandOne.getDiscardingCard().getValue());
    }

    @Test
    public void testGetDiscardingCardWithTwoPossibility() {
        // Hand: 2 2 3 3
        Assert.assertEquals(3, testHandTwo.getDiscardingCard().getValue());
    }

    @Test
    public void testIsWinningHand() {
        CardHand testWinningHand = new CardHand(new int[]{2, 2, 2, 2});
        CardHand testLoosingHandOne = new CardHand(new int[]{2, 2, 2});
        Assert.assertTrue("Expected true, hand: 2 2 2 2", testWinningHand.isWinningHand());
        Assert.assertFalse("Expected false, hand: 2 2 2", testLoosingHandOne.isWinningHand());
        Assert.assertFalse("Expected false, hand: 2 2 2 3", testHandTwo.isWinningHand());
        Assert.assertFalse("Expected false, hand: 1 2 3 4 5", testHandThree.isWinningHand());
    }

    @Test
    public void testIsWinningHandWithEmptyHand() {
        Assert.assertFalse("Expected false: no cards in hand to win", emptyHand.isWinningHand());
    }

    @Test
    public void testRandomCard() {
        // Checks by first getting a random card from player hand, then checks if that card exists in player hand.
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
        // Hand: 2 2 2 2 5
        // Sets preferred as 2 sp should expect 5
        Assert.assertEquals(5, testHandOne.randomCard(2).getValue());
        // Sets preferred as 5 sp should expect 2
        Assert.assertEquals(2, testHandOne.randomCard(5).getValue());
    }
}
