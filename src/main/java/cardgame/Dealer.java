package cardgame;

public class Dealer {

    /**
     * Takes in list of input pack numbers and number of players returns a list containing a list of player hands
     * and a list containing player decks.
     * Example: [[[player1hand], [player2hand], ...], [[player1deck], [player2deck], ...]]
     *
     * @param pack            integer array of card values from input pack file, should be 8 * numberOfPlayers
     * @param numberOfPlayers number of players to deal to
     * @return a list of list of player hand and list of player decks
     */
    public static int[][][] deal(int[] pack, int numberOfPlayers) {

        int[][][] result = new int[2][numberOfPlayers][4];
        int[] cards;

        for (int i = 0; i < numberOfPlayers; i++) {
            cards = new int[4];
            for (int j = 0; j < 4; j++) {
                cards[j] = pack[i + (j * numberOfPlayers)];
            }
            result[0][i] = cards;
        }

        for (int i = numberOfPlayers * 4; i < numberOfPlayers * 5; i++) {
            cards = new int[4];
            for (int j = 0; j < 4; j++) {
                cards[j] = pack[i + (j * numberOfPlayers)];
            }
            result[1][i - (numberOfPlayers * 4)] = cards;
        }
        return result;
    }

}
