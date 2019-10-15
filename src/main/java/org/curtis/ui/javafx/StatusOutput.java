package org.curtis.ui.javafx;

import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.OutputStream;

public class StatusOutput extends OutputStream {
    private TextArea textArea;
    private StringBuilder buffer = new StringBuilder();
    private static final long SLEEP_MILLIS = 100;

    public StatusOutput(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        char input = (char)b;
        buffer.append(input);
    }

    public void clear() {
        clearBuffer();
        textArea.clear();
    }

    public void handle() {
        boolean execute = true;
        while (execute) {
            try {
                flushOutput();
                Thread.sleep(SLEEP_MILLIS);
            } catch (InterruptedException e) {
                execute = false;
            }
        }
    }

    private void flushOutput() {
        if (buffer.length() == 0) return;

        textArea.appendText(buffer.toString());
        clearBuffer();
        textArea.positionCaret(textArea.getLength());
    }

    private void clearBuffer() {
        buffer = new StringBuilder();
    }
}
