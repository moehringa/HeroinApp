package RehabStar.Project.services;

/**
 * Created by David Terkula on 10/24/2017.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyFileReader {

    private String fileName;
    private final static String pathBegin = "src/main/resources/storiesFiles/";
    private byte[] finalText;

    /*
        Constructor for MyFileReader. Initializes with filename and concatenates the relative file path to file name

     */
    public MyFileReader(String fileName) {
        this.fileName = pathBegin + fileName;
    }

    /*
        Reads the file and returns the byte array of the data.

     */
    public byte[] ReadFile() {
        byte[] returnMe = new byte[0];
        File file = new File(fileName);
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(file);
            byte fileContent[] = new byte[(int) file.length()];

            fin.read(fileContent);

            String s = new String(fileContent);
            returnMe = s.getBytes();
            //System.out.println("File content: " + s);

        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading file " + ioe);
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }
        }

        return returnMe;
    }

}


