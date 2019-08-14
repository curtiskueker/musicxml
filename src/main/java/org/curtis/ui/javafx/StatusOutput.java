package org.curtis.ui.javafx;

import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.OutputStream;

public class StatusOutput extends OutputStream {
    private TextArea textArea;
    private StringBuilder buffer = new StringBuilder();
    private int writeCount = 0;
    private static final int WRITE_LIMIT = 1000;

    public StatusOutput(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        writeCount++;
        char input = (char)b;
        if (writeCount >= WRITE_LIMIT) {
            flushOutput();
        } else {
            buffer.append(input);
        }
    }

    public void clear() {
        clearBuffer();
        textArea.clear();
    }

    public void flushOutput() {
        textArea.appendText(buffer.toString());
        clearBuffer();
        textArea.positionCaret(textArea.getLength());
        writeCount = 0;
    }

    private void clearBuffer() {
        buffer = new StringBuilder();
        writeCount = 0;
    }
}
