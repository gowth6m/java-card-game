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
     * Class constructor.
     *
     * @param numberOfPlayers  number of players
     * @param inputPackNumbers integer array of values from input pack
     */
    public CardGame(int numberOfPlayers, int[] inputPackNumbers) {
        this.numberOfPlayers = numberOfPlayers;
        this.inputPackNumbers = inputPackNumbers;
    }

    /**
     * Asks for number of players from command line.
     * Checks if the given number from command line is a valid integer value that is more than one.
     *
     * @return the number of players given in by command line
     */
    public static int askForNumberOfPlayers() {
        Scanner scanner = new Scanner(System.in);
        int n;
        while (true) {
            if (GameLogger.printing) {
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
     * Checks if the input pack is valid and has the right number of card values for the given number of players.
     *
     * @param numberOfPlayers number of players to play the card game
     * @return list of integer of input pack values
     * @throws URISyntaxException if invalid characters when trying to parse a string using FileReader class
     */
    public static int[] askForInputPack(int numberOfPlayers) throws URISyntaxException {
        Scanner scanner = new Scanner(System.in);
        String fileInput;
        int[] pack;
        while (true) {
            if (GameLogger.printing) {
                System.out.print("Please enter location of pack to load: ");
            }
            fileInput = scanner.next();
            // checks if its txt file
            if (fileInput.contains(".txt")) {
                FileReader fr = new FileReader(numberOfPlayers, fileInput);
                // checks if the file exists and is correct format
                if (fr.readAndValidate()) {
                    pack = fr.getListOfNumbers();
                    break;
                } else {
                    System.out.println("Files doesn't exist or incorrect file format!");
                }
            } else {
                System.out.println("Invalid input!");
            }
        }
        return pack;
    }

    /**
     * Constructs a new instance of Player,CardHand and CardDeck for each player and deals CardHands and CardDecks to the player.
     */
    public void initialSetUp() {
        int[][][] dealtCards = Dealer.deal(inputPackNumbers, numberOfPlayers);

        for (int i = 0; i < numberOfPlayers; i++) {
            Player p = new Player(this, new CardHand(dealtCards[0][i]), new CardDeck(dealtCards[1][i]), i + 1);
            listOfPlayers.add(p);
        }
    }

    /**
     * Checks if any player has a winning hand and sets it as winning player if player does.
     * Then starts the player threads if no player has already won, otherwise ends the game for each player.
     */
    public void startGame() {
        for (Player p : listOfPlayers) {
            (new Thread(p)).start();
        }
    }

    /**
     * Returns the next player in the list, loops back to first player if on last player.
     *
     * @param p current player
     * @return player next in line to current player
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
     *
     * @throws URISyntaxException if invalid characters are used when trying to parse a string using FileReader class
     */
    public static void main(String[] args) throws URISyntaxException {
        int numberOfPlayers = askForNumberOfPlayers();
        CardGame game = new CardGame(numberOfPlayers, askForInputPack(numberOfPlayers));
        GameLogger.initLogs();
        game.initialSetUp();
        game.startGame();
    }
}