package cardgame;

public class Card {

    private final int value;

    /**
     * Class constructor.
     *
     * @param value face value of the Card
     */
    public Card(int value) {
        this.value = value;
    }

    /**
     * Getter for face value of Card.
     *
     * @return face value of the Card
     */
    public int getValue() {
        return value;
    }

}
