import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class FileReader {

    final File root = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).toURI());
    private final File file;
    private final List<String> listOfNumbers = new ArrayList<>();

    /**
     * Constructor for FileReader
     * @param fileName name of the input pack by the player
     * @throws URISyntaxException
     */
    public FileReader(String fileName) throws URISyntaxException {
        file = new File(root, fileName);
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNext()) {
                listOfNumbers.add(myReader.next());
            }
            myReader.close();
        } catch(IOException ignored) {}
    }

    /**
     * Gets the list of numbers from input pack.
     * @return the list of numbers from the input pack as a list of Integers.
     */
    public List<Integer> getListOfNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (String s:listOfNumbers) {
            try {
                numbers.add(Integer.parseInt(s));
            } catch(Exception e) {
                System.out.println("ERROR IN CODE (FileReader:getListOfNumbers)");
            }
        }
        return numbers;
    }

    /**
     * Checks the file format, whether it has the right amount of numbers.
     * @return - true if the file contains positive integers and the file has rows equal to 8 * number of players
     */
    public boolean checkFileFormat() {
        for(String number:listOfNumbers) {
            return (Utilities.isInteger(number)) && (Integer.parseInt(number) >= 0) && (this.getListOfNumbers().size() == 8 * CardGame.numberOfPlayers);
        }
        return true;
    }

    /**
     * Checks if the game is winnable by checking if there are 4 of the same cards in the input pack.
     * @return true if game is winnable, false if not.
     */
    public boolean isGameWinnable() {
        boolean winnable = false;
        for(int a:getListOfNumbers()) {
            int counter = 0;
            for(int b:getListOfNumbers()) {
                if(a==b) {
                    counter++;
                }
            }
            if (counter >= 4) {
                winnable = true;
            }
        }
        return winnable;
    }
}
