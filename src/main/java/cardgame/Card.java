package cardgame;

public class Card {

    private final int value;

    /**
     * Constructor for Card.
     * @param v Face value of the Card
     */
    public Card(int v) {
        value = v;
    }

    /**
     * Getter for face value of Card.
     * @return Face value of the Card
     */
    public int getValue(){
        return value;
    }

}
