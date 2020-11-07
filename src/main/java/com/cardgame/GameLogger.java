package com.cardgame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GameLogger {
    public static boolean logging = true;
    public static boolean printing = true;

    /**
     * Constructs a new instance of GameLogger.
     */
    public GameLogger() {
    }

    /**
     * Deletes old output logs and creates directory if not already created.
     */
    public static void initLogs(){
        if(logging) {
            File file = new File("logs/");
            if (!file.exists()) {
                file.mkdirs();
            }
            for (File sf : file.listFiles()) {
                sf.delete();
            }
        }
    }

    /**
     * Logs the given string to text file for the corresponding player given by playerNumber
     * @param name name of the text file
     * @param playerNumber part of the text file name
     * @param fileInput this is the input that gets recorded on the text file
     */
    public synchronized void writeToFile(String name, int playerNumber, String fileInput) {
        if (logging) {
            try {
                File file = new File("logs/" + name + playerNumber + "_output.txt");
                FileWriter fr = new FileWriter(file, true);
                fileInput += "\n";
                fr.append(fileInput);
                fr.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}
