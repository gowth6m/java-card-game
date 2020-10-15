import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class CardGame {

    /**
     *
     * @param args
     * @throws FileNotFoundException
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        File root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
        Scanner scanner = new Scanner(System.in);
        int numberOfPlayers;
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
                // checks if the file exist
//                System.out.println(fileInput);
                if (file.exists() && fr.checkFile()) {
                    System.out.println(fr.myList);
                    break;
                } else {
                    System.out.println("File not found!");
                }
            } else {
                System.out.println("Invalid input!");
            }
        }

        // testing stuff here
        System.out.println(numberOfPlayers);
        System.out.println(fileInput);
//        System.out.println(root);

//        FileReader fr = new FileReader(fileInput);
//        System.out.println(fr.returnList());
//        fr.checkFile();

    }
}
