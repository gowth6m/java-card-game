package main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class FileReader {

    final File root = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).toURI());
    private final List<String> listOfNumbers = new ArrayList<>();

    /**
     * Constructor for FileReader
     * @param fileName name of the input pack by the player
     */
    public FileReader(String fileName) throws URISyntaxException {
        File file = new File(root, fileName);
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNext()) {
                listOfNumbers.add(myReader.next());
            }
            myReader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the list of numbers from input pack.
     * @return the list of numbers from the input pack as a list of Integers.
     */
    public int[] getListOfNumbers() {
        int[] numbers = new int[CardGame.numberOfPlayers * 8];
        for(int i = 0; i < listOfNumbers.size(); i++) {
            numbers[i] = Integer.parseInt(listOfNumbers.get(i));
        }
        return numbers;
    }

    /**
     * Checks the file format, whether it has the right amount of numbers.
     * @return - true if the file contains positive integers and the file has rows equal to 8 * number of players
     */
    public boolean checkFileFormat() {
        for(String number:listOfNumbers) {
            try{
                if(Integer.parseInt(number) < 0 || listOfNumbers.size() != 8 * CardGame.numberOfPlayers){
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