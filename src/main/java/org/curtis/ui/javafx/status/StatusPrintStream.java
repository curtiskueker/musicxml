package org.curtis.ui.javafx.status;

import java.io.IOException;
import java.io.OutputStream;

public class StatusPrintStream extends OutputStream {
    private StringBuilder buffer;
    private boolean printToBuffer;

    public StatusPrintStream(StringBuilder buffer, boolean printToBuffer) {
        this.buffer = buffer;
        this.printToBuffer = printToBuffer;
    }

    @Override
    public void write(int b) throws IOException {
        if (!printToBuffer) return;

        char input = (char)b;
        buffer.append(input);
    }

    public void setPrintToBuffer(boolean printToBuffer) {
        this.printToBuffer = printToBuffer;
    }
}
