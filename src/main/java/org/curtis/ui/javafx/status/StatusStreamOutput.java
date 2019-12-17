package org.curtis.ui.javafx.status;

import java.io.IOException;
import java.io.OutputStream;

public abstract class StatusStreamOutput extends OutputStream {
    protected StringBuilder buffer = new StringBuilder();
    protected boolean printToBuffer = true;

    public StatusStreamOutput() {

    }

    public boolean isPrintToBuffer() {
        return printToBuffer;
    }

    public void setPrintToBuffer(boolean printToBuffer) {
        this.printToBuffer = printToBuffer;
    }

    @Override
    public void write(int b) throws IOException {
        if (!isPrintToBuffer()) return;

        char input = (char)b;
        buffer.append(input);
    }

    protected void clearBuffer() {
        buffer.delete(0, buffer.length());
    }

    public abstract void openStream();
    public abstract void closeStream();
    public abstract void flushStream();
    public abstract void clearStream();
}
