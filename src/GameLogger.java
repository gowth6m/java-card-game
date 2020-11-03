import java.io.*;

public class GameLogger {
    private File file;

    public GameLogger() {}

    /**
     * Logs the given string to text file for the corresponding player given by playerNumber
     * @param name name of the text file
     * @param playerNumber part of the text file name
     * @param fileInput this is the input that gets recorded on the text file
     */
    public synchronized void writeToFile(String name, int playerNumber, String fileInput) {
        try {
            file = new File(name + playerNumber +"_output.txt");
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
