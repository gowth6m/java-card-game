import java.io.File;
import java.net.URISyntaxException;
import java.util.*;

public class CardGame implements Runnable{
    public static int numberOfPlayers;
    public List<Integer> inputPackNumbers;

    // Thread stuff
    public List<Runnable> listOfRunnable = new ArrayList<>();
    public List<Thread> listOfThreads = new ArrayList<>();

    public CardGame() {
    }

    /**
     * Asks for number of players & input pack from command line
     * Also checks for validity for input pack
     * Gets list of input pack numbers and puts it in a 'inputPackNumbers'
     * @throws URISyntaxException
     */
    public void initialSetup() throws URISyntaxException {
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
                    // System.out.println(fr.getListOfNumbers());
                    break;
                } else {
                    System.out.println("Files doesn't exist or incorrect file format!");
                }
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Thread started");
    }

    /**
     *
     * @param args
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws URISyntaxException {
        CardGame game = new CardGame();
        game.initialSetup();

        // JUST FOR TESTING
//        game.numberOfPlayers = 2;
//        FileReader fr = new FileReader("t2.txt");
//        game.inputPackNumbers = fr.getListOfNumbers();

        // ======== TESTING for Dealer methods =====================
        // for 2 players, test with t2.txt
        // for 4 players, test with t4.txt

        // creating player list
        Player[] players = new Player[game.numberOfPlayers];
        for (int i = 0; i < game.numberOfPlayers; i++) {
            players[i] = new Player();
        }

        // setting up thread stuff
        for (int i=0; i < players.length; i++) {
            game.listOfRunnable.add(players[i]);
            game.listOfThreads.add(new Thread(game.listOfRunnable.get(i)));
            game.listOfThreads.add(new Thread(Integer.toString(i)) {
                public void run() {
                }
            });
        }

        for(Thread t: game.listOfThreads) {
            t.start();
        }

        Dealer.dealCardDeck(game.inputPackNumbers, players);
        Dealer.dealPlayerCurrentHand(game.inputPackNumbers, players);

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
