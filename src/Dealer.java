import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class Dealer {
    public Dealer() {}

    /**
     *
     * @param inputPackList - list of input pack numbers
     * @param players - array of current players in the game
     */
    public static void dealPlayerCurrentHand(List inputPackList, Player[] players){
        int numberOfPlayers = players.length;

        for(int i = 0; i < numberOfPlayers; i++){
            int[] temp = new int[4];
            for(int j = 0; j < 4; j++){
                temp[j] = (int) inputPackList.get(i + (j * numberOfPlayers));
            }
            players[i].setCurrentHand(Utilities.intToCardArray(temp));
        }
    }

    /**
     *
     * @param inputPackList
     * @param players
     */
    public static void dealCardDeck(List inputPackList, Player[] players){
        int numberOfPlayers = players.length;
        // getting the second half of the list since first half of the list is used for player's current hand
        List secondHalfInputPack = Utilities.splitList(inputPackList)[1];

        for(int i = 0; i < numberOfPlayers; i++) {
            int[] temp = new int[4];
            for(int j = 0; j < 4; j++){
                temp[j] = (int) secondHalfInputPack.get(i + (j * numberOfPlayers));
            }
            CardDeck cd = new CardDeck(temp);
            players[i].setDeck(cd);
        }
    }
}
