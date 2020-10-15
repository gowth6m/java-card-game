import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    List<String> myList = new ArrayList<>();

    /**
     *
     * @param fileName
     * @throws URISyntaxException
     */
    public FileReader(String fileName) throws URISyntaxException {
        file = new File(root, fileName);
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNext()) {
                myList.add(myReader.next());
            }
            myReader.close();
        } catch(IOException e) {
//            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public boolean checkFile() {
        for(String number:myList) {
            if ((isInteger(number)) && (Integer.parseInt(number) >= 0)) {
//                System.out.println(number);
            } else {
                return false;
            }
        }
        return true;
    }

    public List<String> returnList() {
        return myList;
    }

    // TO CHECK IF ITS AN INTEGER
    public static boolean isInteger(Object object) {
        if(object instanceof Integer) {
            return true;
        } else {
            String string = object.toString();

            try {
                Integer.parseInt(string);
            } catch(Exception e) {
                return false;
            }
        }
        return true;
    }

}
