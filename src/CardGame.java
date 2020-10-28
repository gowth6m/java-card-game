import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGame {
    public static int numberOfPlayers;
    private List<Integer> inputPackNumbers = new ArrayList<>();
    public static final List<Player> listOfPlayers = new ArrayList<>();
    private final List<CardDeck> listOfCardDecks = new ArrayList<>();
    private final List<CardHand> listOfPlayerHands = new ArrayList<>();

    public static volatile boolean gameOver = false;

    public CardGame() { }

    /**
     * Asks for number of players & input pack from command line
     * Also checks for validity for input pack
     * Gets list of input pack numbers and puts it in a 'inputPackNumbers'
     *
     * @throws URISyntaxException if invalid characters when trying to parse the String
     */
    public void askForInputPack() throws URISyntaxException {
        File root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
        Scanner scanner = new Scanner(System.in);
        String fileInput;

        while (true) {
            System.out.print("Please enter the number of players: ");
            String input = scanner.next();
            // check if its an int
            try {
                numberOfPlayers = Integer.parseInt(input);
                if (numberOfPlayers > 1) {
                    break;
                }
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
                    break;
                } else {
                    System.out.println("Files doesn't exist or incorrect file format!");
                }
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

    /**
     * Hands out the initial hand and decks for each player.
     */
    public void initialSetUp() {
        //
        for(int[] list: Dealer.deal(Utilities.intArrToIntList(this.inputPackNumbers), numberOfPlayers)[0]) {
            this.listOfPlayerHands.add(new CardHand(list));
        }

        for(int[] list: Dealer.deal(Utilities.intArrToIntList(this.inputPackNumbers), numberOfPlayers)[1]) {
            this.listOfCardDecks.add(new CardDeck(list));
        }

        for(int i = 0; i < numberOfPlayers; i++) {
            this.listOfPlayers.add(new Player(this.listOfPlayerHands.get(i), this.listOfCardDecks.get(i)));
//            System.out.println(this.listOfPlayers.get(i));
        }

    }

    public static Player getNextPlayer(Player p){
        int i = CardGame.listOfPlayers.indexOf(p) + 1;
        if(i > CardGame.listOfPlayers.size() - 1){
            return CardGame.listOfPlayers.get(0);
        } else {
            return CardGame.listOfPlayers.get(i);
        }
    }

    /**
     *
     * @param args
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws URISyntaxException {
        CardGame game = new CardGame();
        game.askForInputPack();
        game.initialSetUp();
        // TESTING STUFF
        for(Player p:game.listOfPlayers){
            (new Thread(p)).start();
        }
    }
}
