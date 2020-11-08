package cardgame;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CardGame {
    public int numberOfPlayers;
    public int[] inputPackNumbers;
    public final List<Player> listOfPlayers = new ArrayList<>();
    public AtomicInteger winningPlayer = new AtomicInteger(0);

    /**
     * Constructs an instance of CardGame.
     * @param players Number of players
     * @param pack Integer array of pack values
     */
    public CardGame(int players, int[] pack) {
        numberOfPlayers = players;
        inputPackNumbers = pack;
    }

    /**
     * Asks for number of players from command line.
     * Also checks if its an integer and valid.
     *
     */
    public static int askForNumberOfPlayers() {
        Scanner scanner = new Scanner(System.in);
        int n;
        while (true) {
            if(GameLogger.printing) {
                System.out.print("Please enter the number of players: ");
            }
            String input = scanner.next();
            // To check if its an int
            try {
                n = Integer.parseInt(input);
                if (n > 1) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
        }
        return n;
    }

    /**
     * Ask for inputPack from command line.
     * Also checks if the input pack is valid and has the right amount of numbers.
     *
     * @throws URISyntaxException if invalid characters when trying to parse the String
     */
    public static int[] askForInputPack(int numberOfPlayers) throws URISyntaxException {
        Scanner scanner = new Scanner(System.in);
        String fileInput;
        int[] p;
        while (true) {
            if(GameLogger.printing) {
                System.out.print("Please enter location of pack to load: ");
            }
            fileInput = scanner.next();
            // checks if its txt file
            if (fileInput.contains(".txt")) {
                FileReader fr = new FileReader(numberOfPlayers, fileInput);
                // checks if the file exists and is correct format
                if (fr.readAndValidate()) {
                    p = fr.getListOfNumbers();
                    System.out.println("Is game winnable? " + fr.isGameWinnable());
                    break;
                } else {
                    System.out.println("Files doesn't exist or incorrect file format!");
                }
            } else {
                System.out.println("Invalid input!");
            }
        }
        return p;
    }

    /**
     * Constructs a new instance of Player for each player and deals CardHands and CardDecks.
     */
    public void initialSetUp() {
       int[][][] dealtCards = Dealer.deal(inputPackNumbers, numberOfPlayers);

       for(int i = 0; i < numberOfPlayers; i++){
           Player p = new Player(this, new CardHand(dealtCards[0][i]), new CardDeck(dealtCards[1][i]), i + 1);
           listOfPlayers.add(p);
       }
    }

    /**
     * Starts all player threads.
     */
    public void startGame() {
        for(Player p:listOfPlayers){
            if(p.getHand().isWinningHand() && winningPlayer.get() == 0){
                winningPlayer.set(p.getPlayerNumber());
            }
        }
        if(winningPlayer.get() == 0) {
            for (Player p : listOfPlayers) {
                (new Thread(p)).start();
            }
        } else {
            for(Player p:listOfPlayers){
                p.end();
            }
        }
    }

    /**
     * Checks player hand at the start to see if anyone has a winning hand so they instantly win.
     */
    public void checkForWinAtStart() {
        for(Player p:listOfPlayers) {
            if(p.getHand().isWinningHand()) {
                winningPlayer.set(p.getPlayerNumber());
                System.out.println("player "+winningPlayer+" wins");
                break;
            }
        }
    }

    /**
     * Returns the next player in the list, loops back to first player if on last player.
     * @param p Current player
     * @return Player next in line to current player
     */
    public Player getNextPlayer(Player p) {
        int i = listOfPlayers.indexOf(p) + 1;
        if (i > listOfPlayers.size() - 1) {
            return listOfPlayers.get(0);
        } else {
            return listOfPlayers.get(i);
        }
    }

    /**
     * Main method.
     */
    public static void main(String[] args) throws URISyntaxException {
        int numberOfPlayers = askForNumberOfPlayers();
        CardGame game = new CardGame(numberOfPlayers, askForInputPack(numberOfPlayers));
        GameLogger.initLogs();
        game.initialSetUp();
        game.checkForWinAtStart();
        game.startGame();
    }
}