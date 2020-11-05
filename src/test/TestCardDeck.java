package test;

import main.Card;
import main.CardDeck;
import org.junit.Assert;
import org.junit.Test;

public class TestCardDeck {
    private int[] numbersInDeck = {1,2,3,4,5,6,7,8};
    private CardDeck testDeck = new CardDeck(numbersInDeck);
    private Card testCard = new Card(9);

    @Test
    public void testGetStringOfCardValues() {
        int[] deckNumbers = {1,2,3,4};
        CardDeck deck = new CardDeck(deckNumbers);
        Assert.assertEquals("1 2 3 4", deck.getStringOfCardValues());
    }

    @Test
    public void testAddCard() {
        Assert.assertEquals("1 2 3 4 5 6 7 8", testDeck.getStringOfCardValues());
        testDeck.addCard(testCard);
        Assert.assertEquals("1 2 3 4 5 6 7 8 9", testDeck.getStringOfCardValues());
    }

    @Test
    public void testRemoveCard() {
        Assert.assertEquals("1 2 3 4 5 6 7 8", testDeck.getStringOfCardValues());
        testDeck.addCard(testCard);
        Assert.assertEquals("1 2 3 4 5 6 7 8 9", testDeck.getStringOfCardValues());
        testDeck.removeCard(testCard);
        Assert.assertEquals("1 2 3 4 5 6 7 8", testDeck.getStringOfCardValues());
    }

    @Test
    public void testPop() {
        Assert.assertEquals("1 2 3 4 5 6 7 8", testDeck.getStringOfCardValues());
        testDeck.pop();
        Assert.assertEquals("2 3 4 5 6 7 8", testDeck.getStringOfCardValues());
    }

    @Test
    public void testIsEmpty() {
        CardDeck emptyDeck = new CardDeck();
        Assert.assertTrue("Error, deck is not empty (isEmpty failed)",emptyDeck.isEmpty());
    }
}
