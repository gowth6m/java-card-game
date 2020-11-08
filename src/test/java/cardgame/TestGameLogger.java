package cardgame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestGameLogger {
    private final GameLogger testLogger = new GameLogger();
    private final String testPlayerName = "testPlayer";
    private final int testPlayerNumber = 0;
    private final ArrayList<String> fileLinesInList = new ArrayList<>();

    @Before
    public void setUp() {
        GameLogger.logging = true;
        GameLogger.initLogs();
        testLogger.writeToFile(testPlayerName, testPlayerNumber, "Test input string one");
        testLogger.writeToFile(testPlayerName, testPlayerNumber, "Test input string two");
    }

    /**
     * Tests the GameLogger by first writing test text, then reading it and checking if the strings in the files match.
     */
    @Test
    public void testWriteToFile() {
        File file = new File("logs/" + testPlayerName + testPlayerNumber + "_output.txt");
        if (!file.exists()) {
            Assert.fail("No log files found");
        }
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNext()) {
                fileLinesInList.add(myReader.nextLine());
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("Test input string one", fileLinesInList.get(0));
        Assert.assertEquals("Test input string two", fileLinesInList.get(1));
    }
}
