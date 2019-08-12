package org.curtis.ui.swing;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class SwingStatusOutput extends OutputStream {
    private JTextArea textArea;

    public SwingStatusOutput(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char)b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
