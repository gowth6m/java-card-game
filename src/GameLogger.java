import java.io.*;

public class GameLogger {
    File file;

    public GameLogger() {}

    /**
     * Logs the given string to text file for the corresponding player given by playerNumber.
     * @param playerNumber - The players number.
     * @param fileInput - This is the input that gets recorded on the text file.
     */
    public void writeToFile(int playerNumber, String fileInput) {
        try {
            file = new File("player"+Integer.toString(playerNumber)+"_output");
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            pr.println(fileInput);
            pr.close();
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
