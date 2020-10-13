import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Working on this to check the text file is correct and stuff

public class FileReader {
    final File root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
    private File file;
    List<Integer> myList = new ArrayList<>();

    public FileReader(String fileName) throws URISyntaxException {
        file = new File(root, fileName);
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextInt()) {
                myList.add(myReader.nextInt());
            }
            myReader.close();
        } catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public boolean checkFile() {
        return true;
    }

    public List<Integer> returnList() {
        return myList;
    }

}
