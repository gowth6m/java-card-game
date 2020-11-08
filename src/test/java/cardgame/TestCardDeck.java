package cardgame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCardDeck {
    private final CardDeck testDeck = new CardDeck(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
    private final CardDeck emptyDeck = new CardDeck(new int[]{});
    private final Card testCard = new Card(9);

    @Before
    public void setUp() {
        GameLogger.logging = false;
        GameLogger.printing = false;
    }

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
    public void testAddCardToEmptyDeck() {
        Assert.assertEquals("", emptyDeck.getStringOfCardValues());
        emptyDeck.addCard(testCard);
        Assert.assertEquals("9", emptyDeck.getStringOfCardValues());
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
    public void testRemoveCardFromEmptyDeck() {
        Assert.assertEquals("", emptyDeck.getStringOfCardValues());
        emptyDeck.removeCard(testCard);
        Assert.assertEquals("", emptyDeck.getStringOfCardValues());
    }

    @Test
    public void testPop() {
        Assert.assertEquals("1 2 3 4 5 6 7 8", testDeck.getStringOfCardValues());
        testDeck.pop();
        Assert.assertEquals("2 3 4 5 6 7 8", testDeck.getStringOfCardValues());
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue("Deck is not empty (isEmpty failed)",emptyDeck.isEmpty());
        Assert.assertFalse("Deck is empty (isEmpty failed)",testDeck.isEmpty());
    }
}
