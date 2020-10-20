import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class CardGame {
    public static int numberOfPlayers;
    public static List<Integer> inputPackNumbers;
    /**
     *
     * @param args
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws URISyntaxException {
        File root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
        Scanner scanner = new Scanner(System.in);
        String fileInput;

        while (true) {
            System.out.print("Please enter the number of players: ");
            String input = scanner.next();
            // check if its an int
            try {
                numberOfPlayers = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
        }

        while (true) {
            System.out.print("Please enter location of pack to load: ");
            fileInput = scanner.next();
            File file = new File(root, fileInput);
            // checks if its txt file
            if (fileInput.contains(".txt")) {
                FileReader fr = new FileReader(fileInput);
                // checks if the file exists and is correct format
                if (file.exists() && fr.checkFileFormat()) {
                    inputPackNumbers = fr.getListOfNumbers();
                    System.out.println(fr.getListOfNumbers());
                    break;
                } else {
                    System.out.println("Files doesn't exist or incorrect file format!");
                }
            } else {
                System.out.println("Invalid input!");
            }
        }

        // ======== TESTING ========================================
        // System.out.println(numberOfPlayers);
//        System.out.println(fileInput);

        // ======= TESTING for Dealer methods =====================

        // >>>>>>>>> !!!!! READ THIS:!!!!!! <<<<<<<<<<<
        // for 2 players, test with t2.txt
        // for 4 players, test with t4.txt

        // creating player list
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player();
        }

        Dealer.dealCardDeck(inputPackNumbers, players);
        Dealer.dealPlayerCurrentHand(inputPackNumbers, players);

        for(Player p: players) {
            System.out.println(p + " Player Hand:");
            for(Card c:p.getCurrentHand()) {
                System.out.print(c.getNumber()+ ", ");
            }
            System.out.println("\n"+"Card Deck:");

            for(Card c:p.getDeck().getCards()) {
                System.out.print(c.getNumber()+ ", ");
            }
            System.out.println("\n");
        }

    }
}
