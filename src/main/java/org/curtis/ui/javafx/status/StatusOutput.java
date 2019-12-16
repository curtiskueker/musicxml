package org.curtis.ui.javafx.status;

import javafx.scene.control.TextArea;

import java.io.PrintStream;

public class StatusOutput {
    public static final boolean PRINT_ON = true;
    public static final boolean PRINT_OFF = false;

    private StringBuilder buffer = new StringBuilder();

    private StatusPrintStream errStream = new StatusPrintStream(buffer, PRINT_ON);
    private StatusPrintStream outStream = new StatusPrintStream(buffer, PRINT_OFF);

    private TextArea textArea;
    private static final long SLEEP_MILLIS = 500;

    public StatusOutput(TextArea textArea) {
        this.textArea = textArea;
        System.setErr(new PrintStream(errStream));
        System.setOut(new PrintStream(outStream));
    }

    public StatusPrintStream getOutStream() {
        return outStream;
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

    public void clear() {
        clearBuffer();
        textArea.clear();
    }

    private void flushOutput() {
        if (buffer.length() == 0) return;

        textArea.appendText(buffer.toString());
        clearBuffer();
        textArea.positionCaret(textArea.getLength());
    }

    private void clearBuffer() {
        buffer.delete(0, buffer.length());
    }
}
