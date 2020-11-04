import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CardGame {
    public static int numberOfPlayers;
    private int[] inputPackNumbers;
    public static final List<Player> listOfPlayers = new ArrayList<>();
    public static AtomicInteger winningPlayer = new AtomicInteger(0);

    public CardGame() { }

    /**
     * Asks for number of players & input pack from command line
     * Also checks for validity for input pack
     * Gets list of input pack numbers and puts it in a 'inputPackNumbers'
     *
     * @throws URISyntaxException if invalid characters when trying to parse the String
     */
    public void askForInputPack() throws URISyntaxException {
        File root = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).toURI());
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
                    System.out.println("Is game winnable? " + fr.isGameWinnable());
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
       int[][][] dealtCards = Dealer.deal(inputPackNumbers, numberOfPlayers);

       for(int i = 0; i < numberOfPlayers; i++){
           Player p = new Player(new CardHand(dealtCards[0][i]), new CardDeck(dealtCards[1][i]), i + 1);
           listOfPlayers.add(p);
       }
    }

    /**
     * Returns the next player in the list, loops back to first player if on last player.
     * @param p Current player
     * @return Player next in line to current player
     */
    public static Player getNextPlayer(Player p){
        int i = CardGame.listOfPlayers.indexOf(p) + 1;
        if(i > CardGame.listOfPlayers.size() - 1){
            return CardGame.listOfPlayers.get(0);
        } else {
            return CardGame.listOfPlayers.get(i);
        }
    }

    public static void endGame(int playerNumber){
        winningPlayer.set(playerNumber);
    }

    /**
     * Main method
     * @param args
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        CardGame game = new CardGame();
        GameLogger.initLogs();
        game.askForInputPack();
        game.initialSetUp();

        for(Player p:listOfPlayers){
            (new Thread(p)).start();
        }
    }
}

// TODO THINGS TO FIX
/*
    - more than 1 player can win at the same time (need to check on that)
    - cards disappear from the player deck after lots of rounds.
    - what to do if no possibility of winning?
 */