package org.curtis.ui;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class StatusOutput extends OutputStream {
    private JTextArea textArea;

    public StatusOutput(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char)b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
