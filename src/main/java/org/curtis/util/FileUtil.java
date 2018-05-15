package org.curtis.util;

import org.curtis.exception.FileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    private FileUtil() {

    }

    public static void stringToFile(String input, String filename) throws FileException {
        if (input == null) throw new FileException("File input is null");

        File file = new File(filename);
        FileWriter fileWriter = null;

        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Error creating new file " + filename);
                }
            }

            fileWriter = new FileWriter(file);

            fileWriter.write(input);
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                //
            }
        }
    }
}
