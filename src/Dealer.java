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
//            System.out.println(Arrays.toString(temp));
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


//    public static void main(String [] args) throws URISyntaxException {
//        int[] lol = {1, 3, 4};
////        CardDeck deck = new CardDeck(lol);
//        Player p1 = new Player();
//        Player p2 = new Player();
//        Player p3 = new Player();
//        Player p4 = new Player();
//
//
//        Player[] pList = {p1, p2};
//        FileReader fr = new FileReader("test.txt");
//
//        dealPlayerCurrentHand(fr.getListOfNumbers(), pList);
////        System.out.println("============");
//
//        Card[] lol1 = pList[0].getCurrentHand();
////        System.out.println(lol1);
////        for(Card i:lol1) {
////            System.out.println(i.getNumber());
////        }
////
////        for(Card i:pList[1].getCurrentHand()) {
////            System.out.println(i.getNumber());
////        }
////
////        for(Card i:pList[2].getCurrentHand()) {
////            System.out.println(i.getNumber());
////        }
//        System.out.println(fr.getListOfNumbers());
////        System.out.println(p1.getDeck().getCards());
//        dealCardDeck(fr.getListOfNumbers(), pList);
//
//        for (Card i:pList[1].getDeck().getCards()) {
//            System.out.println(i.getNumber());
//        }
//
//    }
}
