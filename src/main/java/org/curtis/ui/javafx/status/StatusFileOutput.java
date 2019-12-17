package org.curtis.ui.javafx.status;

import org.curtis.exception.FileException;
import org.curtis.util.FileUtil;

import java.io.FileWriter;
import java.io.IOException;

public class StatusFileOutput extends StatusStreamOutput {
    private FileWriter writer;
    private String filename;

    public StatusFileOutput(String filename) {
        this.filename = filename;
    }

    public void openStream() {
        try {
            writer = FileUtil.newFileWriter(filename);
        } catch (FileException e) {
            System.err.println("File open error: " + e.getMessage());
        }
    }

    public void closeStream() {
        try {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing file: " + e.getMessage());
        }
    }

    public void flushStream() {
        if (buffer.length() == 0) return;

        try {
            if (writer != null) {
                writer.write(buffer.toString());
                writer.flush();
            }
            clearBuffer();
        } catch (IOException e) {
            System.err.println("Error flushing file output");
        }
    }

    public void clearStream() {
        flushStream();
    }
}
