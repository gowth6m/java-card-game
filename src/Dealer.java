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
            System.out.println(Arrays.toString(temp));
            players[i].setCurrentHand(Utilities.intToCardArray(temp));
        }
    }


    public static void main(String [] args) throws URISyntaxException {
        CardDeck deck = new CardDeck();
        Player p1 = new Player(deck);
        Player p2 = new Player(deck);
        Player p3 = new Player(deck);
        Player p4 = new Player(deck);


        Player[] pList = {p1, p2};
        FileReader fr = new FileReader("test.txt");

        dealPlayerCurrentHand(fr.getListOfNumbers(), pList);
        System.out.println("============");

        Card[] lol1 = pList[1].getCurrentHand();
//        System.out.println(lol1);
        for(Card i:lol1) {
            System.out.println(i.getNumber());
        }

    }
}
