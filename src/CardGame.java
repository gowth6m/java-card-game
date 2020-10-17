import java.io.File;
import java.net.URISyntaxException;
import java.util.Scanner;

public class CardGame {
    public static int numberOfPlayers;
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
                    System.out.println(fr.getListOfNumbers());
                    break;
                } else {
                    System.out.println("Files doesn't exist or incorrect file format!");
                }
            } else {
                System.out.println("Invalid input!");
            }
        }

        // testing stuff here
        System.out.println(numberOfPlayers);
        System.out.println(fileInput);
//        System.out.println(root);
        CardDeck deck1 = new CardDeck();
        Player a = new Player(deck1);
        System.out.println(a.getCurrentCards());

//        FileReader fr = new FileReader(fileInput);
//        System.out.println(fr.returnList());
//        fr.checkFile();

    }
}
