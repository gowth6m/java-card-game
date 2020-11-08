package cardgame;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    private final String name;
    private final List<String> listOfNumbers = new ArrayList<>();
    private final int numberOfPlayers;
    // File pathing for jar
    private final File rootForJar = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
    private final String pathForJar = rootForJar.getParent();
    // File pathing for resource folder in main and test
    private final String pathToMainResource = Paths.get("src","main","resources").toFile().getAbsolutePath();
    private final String pathToTestResource = Paths.get("src","test","resources").toFile().getAbsolutePath();

    /**
     * Constructor for FileReader
     * @param fileName name of the input pack by the player
     */
    public FileReader(int numberOfPlayers, String fileName) throws URISyntaxException {
        this.numberOfPlayers = numberOfPlayers;
        name = fileName;
    }

    /**
     * Gets the list of numbers from input pack.
     * @return the list of numbers from the input pack as a list of Integers.
     */
    public int[] getListOfNumbers() {
        int[] numbers = new int[numberOfPlayers * 8];
        for(int i = 0; i < listOfNumbers.size(); i++) {
            numbers[i] = Integer.parseInt(listOfNumbers.get(i));
        }
        return numbers;
    }

    /**
     * Checks if text file exists in 3 different locations and sets the one where it exist as the path.
     * @return file from either same path as jar file, main resource folder or test resource folder
     */
    public File fileLocator() {
        File file;
        File fileForJar = new File(pathForJar,name);
        File fileForMainResource = new File(pathToMainResource,name);
        File fileForTestResource = new File(pathToTestResource,name);
        if (fileForJar.exists()) {
            file = fileForJar;
        } else if (fileForMainResource.exists()){
            file = fileForMainResource;
        } else {
            file = fileForTestResource;
        }
        return file;
    }

    /**
     * Checks the file format, whether it has the right amount of numbers.
     * @return - true if the file contains positive integers and the file has rows equal to 8 * number of players
     */
    public boolean readAndValidate() {
        if (!fileLocator().exists()){
            return false;
        }
        try {
            Scanner myReader = new Scanner(fileLocator());
            while (myReader.hasNext()) {
                listOfNumbers.add(myReader.next());
            }
            myReader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        for(String number:listOfNumbers) {
            try{
                if(Integer.parseInt(number) < 0 || listOfNumbers.size() != 8 * numberOfPlayers){
                    return false;
                }
            } catch (NumberFormatException ignored) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the game is winnable by checking if there are 4 of the same cards in the input pack.
     * @return true if game is winnable, false if not.
     */
    public boolean isGameWinnable() {
        for(int a:getListOfNumbers()) {
            int counter = 0;
            for(int b:getListOfNumbers()) {
                if(a==b) {
                    counter++;
                }
            }
            if (counter >= 4) {
                return true;
            }
        }
        return false;
    }
}
