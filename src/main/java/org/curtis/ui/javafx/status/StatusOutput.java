package org.curtis.ui.javafx.status;

import javafx.scene.control.TextArea;

public class StatusOutput {
    private StatusOutputHandler outputHandler;

    private static final long SLEEP_MILLIS = 500;

    public StatusOutput(TextArea textArea) {
        outputHandler = new StatusOutputHandler(textArea);
    }

    public void handle() {
        boolean execute = true;
        while (execute) {
            try {
                flush();
                Thread.sleep(SLEEP_MILLIS);
            } catch (InterruptedException e) {
                execute = false;
            }
        }
    }

    public void resetOutputHandler() {
        outputHandler.reset();
    }

    public void printToOutputStream(boolean printOutput) {
        outputHandler.printToOutputStream(printOutput);
    }

    public void clear() {
        outputHandler.clear();
    }

    private void flush() {
        outputHandler.flush();
    }
}
