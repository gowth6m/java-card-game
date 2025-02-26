package cardgame;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    private final String fileName;
    private final List<String> listOfNumbers = new ArrayList<>();
    private final int numberOfPlayers;
    // File pathing for jar
    private final File rootForJar = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
    private final String pathForJar = rootForJar.getParent();
    // File pathing for resource folder in main and test
    private final String pathToMainResource = Paths.get("src", "main", "resources").toFile().getAbsolutePath();
    private final String pathToTestResource = Paths.get("src", "test", "resources").toFile().getAbsolutePath();
    private final String pathToResource = Paths.get("resources").toFile().getAbsolutePath();

    /**
     * Class constructor specifying number of players and file name of the text file.
     *
     * @param numberOfPlayers the number of player playing the game
     * @param fileName        name of input pack
     * @throws URISyntaxException if invalid characters when trying to parse a string
     */
    public FileReader(int numberOfPlayers, String fileName) throws URISyntaxException {
        this.numberOfPlayers = numberOfPlayers;
        this.fileName = fileName;
    }

    /**
     * Gets the list of numbers from input pack.
     *
     * @return the list of numbers from the input pack as a list of Integers
     */
    public int[] getListOfNumbers() {
        int[] numbers = new int[numberOfPlayers * 8];
        for (int i = 0; i < listOfNumbers.size(); i++) {
            numbers[i] = Integer.parseInt(listOfNumbers.get(i));
        }
        return numbers;
    }

    /**
     * Checks if text file exists in four different locations and sets the one where it exist as the current root path
     * and creates a file using that current root path and the name given when constructing FileReader.
     * <p>
     * This is to ensure that text file can be found when running this program as jar file or when running from
     * IDE where main resource folder needs to be used instead or for JUnit tests where test resource folder is used.
     * Also the the path can be given to find the text file.
     *
     * @return file created from either same path as jar file, main resource folder, test resource folder or from path
     */
    public File fileLocator() {
        File file;
        File fileForJar = new File(pathForJar, fileName);
        File fileForMainResource = new File(pathToMainResource, fileName);
        File fileForTestResource = new File(pathToTestResource, fileName);
        File fileForResource = new File(pathToResource, fileName);
        File fileForPathGivenByUser = new File(fileName);
        if (fileForJar.exists()) {
            file = fileForJar;
        } else if (fileForMainResource.exists()) {
            file = fileForMainResource;
        } else if (fileForTestResource.exists()) {
            file = fileForTestResource;
        } else if (fileForResource.exists()) {
            file = fileForResource;
        } else {
            file = fileForPathGivenByUser;
        }
        return file;
    }

    /**
     * Checks the file format - whether it has the right number of values according to the number of given players
     * where the requirement of file has to have rows equal to 8 * number of players must be true.
     *
     * @return - true if the file contains positive integers and the file has rows equal to 8 * number of players
     */
    public boolean readAndValidate() {
        File file = fileLocator();
        if (!file.exists()) {
            return false;
        }
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNext()) {
                listOfNumbers.add(myReader.next());
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String number : listOfNumbers) {
            try {
                if (Integer.parseInt(number) < 0 || listOfNumbers.size() != 8 * numberOfPlayers) {
                    return false;
                }
            } catch (NumberFormatException ignored) {
                return false;
            }
        }
        return true;
    }
}
