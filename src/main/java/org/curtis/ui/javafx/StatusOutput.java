package org.curtis.ui.javafx;

import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.OutputStream;

public class StatusOutput extends OutputStream {
    private TextArea textArea;

    public StatusOutput(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        textArea.appendText(String.valueOf((char)b));
        textArea.positionCaret(textArea.getLength());
    }

    public void clear() {
        textArea.clear();
    }
}
