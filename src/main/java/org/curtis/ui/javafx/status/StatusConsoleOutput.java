package org.curtis.ui.javafx.status;

import javafx.scene.control.TextArea;

public class StatusConsoleOutput extends StatusStreamOutput {
    private TextArea textArea;

    public StatusConsoleOutput(TextArea textArea) {
        this.textArea = textArea;
    }

    public void openStream() {
    }

    public void closeStream() {
    }

    public void flushStream() {
        if (buffer.length() == 0) return;

        textArea.appendText(buffer.toString());
        clearBuffer();
        textArea.positionCaret(textArea.getLength());
    }

    public void clearStream() {
        flushStream();
        textArea.clear();
    }
}
