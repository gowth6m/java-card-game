import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    final File root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
    private File file;
    private List<String> listOfNumbers = new ArrayList<>();

    /**
     *
     * @param fileName - name of the input pack by the player
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
        } catch(IOException e) {
            // e.printStackTrace();
        }
    }

    /**
     *
     * @return - file of the input pack
     */
    public File getFile() {
        return file;
    }

    /**
     *
     * @return - the list of numbers from the input pack as a list of strings
     */
    public List<String> getListOfNumbers() {
        return listOfNumbers;
    }

    /**
     *
     * @return - true if the file contains positive integers and the file has rows equal to 8 * number of players
     */
    public boolean checkFileFormat() {
        for(String number:listOfNumbers) {
            if ((Utilities.isInteger(number)) && (Integer.parseInt(number) >= 0) && (this.getListOfNumbers().size() == 8*CardGame.numberOfPlayers)) {
            } else {
                // System.out.println("Incorrect file format!");
                return false;
            }
        }
        return true;
    }
}
