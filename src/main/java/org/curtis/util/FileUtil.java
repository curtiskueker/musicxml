package org.curtis.util;

import org.curtis.exception.FileException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    private FileUtil() {

    }

    public static void stringToFile(String input, String filename) throws FileException {
        if (input == null) throw new FileException("File input is null");

        File file = new File(filename);
        FileWriter fileWriter = null;

        file.getParentFile().mkdir();

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

    public static void moveFile(String source, String destination) throws FileException {
        File fromFile = new File(source);
        if (!fromFile.exists()) throw new FileException("Source file not found.");
        File toFile = new File(destination);
        copyFile(fromFile, toFile);
        if (!toFile.exists()) throw new FileException("Destination file not found.");
        if (!fromFile.delete()) throw new FileException("Temp file not deleted.");
    }

    public static void copyFile(File source, File destination) throws FileException {
        try {
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(destination);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            throw new FileException(e);
        }
    }

    public static String getTempFilename(String extension) {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "." + extension;
    }
}
