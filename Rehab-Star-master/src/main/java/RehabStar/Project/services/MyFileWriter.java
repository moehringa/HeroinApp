package RehabStar.Project.services;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by David Terkula on 10/24/2017.
 */
public class MyFileWriter {
    private String fileName;
    private final String pathBegin = "src/main/resources/storiesFiles/";

    /*
        Constructor for MyFileWriter. Initializes with filename and concatenates the relative file path to file name

     */
    public MyFileWriter(String fileName){
        this.fileName = pathBegin + fileName;
    }

    /*
       Writes the given string to the file as a byte array.

     */
    public void writeToFile(String s) throws IOException {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            byte[] strToBytes = s.getBytes();
            outputStream.write(strToBytes);
            outputStream.close();
    }
}
