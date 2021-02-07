package org.curtis.util;

import org.curtis.exception.FileException;
import org.curtis.musicxml.score.Score;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    private FileUtil() {

    }

    public static File getFile(String filename) throws FileException {
        if (StringUtil.isEmpty(filename)) throw new FileException("Filename is empty");

        File file = new File(filename);
        if (!file.exists()) throw new FileException("File does not exist: " + filename);

        return file;
    }

    public static FileWriter newFileWriter(String filename) throws FileException {
        try {
            File file = new File(filename);
            return new FileWriter(file);
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
    }

    public static void stringToFile(String input, String filename) throws FileException {
        stringToFile(input, filename, Score.DEFAULT_CHARSET);
    }

    public static void stringToFile(String input, String filename, Charset charset) throws FileException {
        if (input == null) throw new FileException("File input is empty");

        File file = new File(filename);
        FileWriter fileWriter = null;

        try {
            file.getParentFile().mkdir();
        } catch (Exception e) {
            // ignore attempt to create directory, if it fails
        }

        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new IOException("Error creating new file " + filename);
                }
            }

            fileWriter = new FileWriter(file, charset);

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
        File fromFile = getFile(source);
        File toFile = new File(destination);
        copyFile(fromFile, toFile);
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
            throw new FileException(e.getMessage());
        }
    }

    public static String getTempFilename(String extension) {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "." + extension;
    }

    public static String getFileExtension(String filename) {
        return StringUtil.isEmpty(filename) ? "" : filename.substring(filename.lastIndexOf(".") + 1);
    }

    public static boolean isXmlFileExtension(String filename) {
        return getFileExtension(filename).equals("xml") || getFileExtension(filename).equals("musicxml");
    }

    public static boolean isLyFileExtension(String filename) {
        return getFileExtension(filename).equals("ly");
    }

    public static boolean isPdfFileExtension(String filename) {
        return getFileExtension(filename).equals("pdf");
    }
}
