package main;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CardGame {
    public static int numberOfPlayers;
    public static int[] inputPackNumbers;
    public static final List<Player> listOfPlayers = new ArrayList<>();
    public static AtomicInteger winningPlayer = new AtomicInteger(0);

    public CardGame() { }

    /**
     * Asks for number of players from command line.
     * Also checks if its an integer and valid.
     *
     */
    public void askForNumberOfPlayers() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if(GameLogger.printing) {
                System.out.print("Please enter the number of players: ");
            }
            String input = scanner.next();
            // To check if its an int
            try {
                numberOfPlayers = Integer.parseInt(input);
                if (numberOfPlayers > 1) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
        }
    }

    /**
     * Ask for inputPack from command line.
     * Also checks if the input pack is valid and has the right amount of numbers.
     *
     * @throws URISyntaxException if invalid characters when trying to parse the String
     */
    public void askForInputPack() throws URISyntaxException {
        Scanner scanner = new Scanner(System.in);
        String fileInput;
        while (true) {
            if(GameLogger.printing) {
                System.out.print("Please enter location of pack to load: ");
            }
            fileInput = scanner.next();
            // checks if its txt file
            if (fileInput.contains(".txt")) {
                FileReader fr = new FileReader(fileInput);
                // checks if the file exists and is correct format
                if (fr.checkFileFormat()) {
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
     * Constructs a new instance of Player for each player and deals CardHands and CardDecks.
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
    public static Player getNextPlayer(Player p) {
        int i = CardGame.listOfPlayers.indexOf(p) + 1;
        if (i > CardGame.listOfPlayers.size() - 1) {
            return CardGame.listOfPlayers.get(0);
        } else {
            return CardGame.listOfPlayers.get(i);
        }
    }

//    public static Player getNextPlayingPlayer(Player p) {
//        int i = CardGame.listOfPlayers.indexOf(p) + 1;
//        if (i > CardGame.listOfPlayers.size() - 1) {
//            if (CardGame.listOfPlayers.get(0).isPlayingGame()) {
//                return CardGame.listOfPlayers.get(0);
//            } else {
//                return getNextPlayingPlayer(CardGame.listOfPlayers.get(0));
//            }
//        } else {
//            if (CardGame.listOfPlayers.get(i).isPlayingGame()) {
//                return CardGame.listOfPlayers.get(i);
//            } else {
//                return getNextPlayingPlayer(CardGame.listOfPlayers.get(i));
//            }
//        }
//    }

    /**
     * Main method.
     */
    public static void main(String[] args) throws URISyntaxException {
        CardGame game = new CardGame();
        GameLogger.initLogs();
        game.askForNumberOfPlayers();
        game.askForInputPack();

        // TODO (For Testing Only)
//        numberOfPlayers = 16;
//        FileReader fr = new FileReader("t16.txt");
//        game.inputPackNumbers = fr.getListOfNumbers();

        game.initialSetUp();

        for(Player p:listOfPlayers){
            (new Thread(p)).start();
        }
    }
}