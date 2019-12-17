package org.curtis.ui.javafx.status;

import javafx.scene.control.TextArea;
import org.curtis.properties.PropertiesConstants;
import org.curtis.properties.PropertiesHandler;
import org.curtis.ui.javafx.form.FormNode;

import java.io.PrintStream;

public class StatusOutputHandler {
    private TextArea textArea;
    private StatusStreamOutput errStream;
    private StatusStreamOutput outStream;

    public StatusOutputHandler(TextArea textArea) {
        this.textArea = textArea;
        reset();
    }

    public void reset() {
        flush();
        close();

        boolean currentOutPrint = outStream != null && outStream.isPrintToBuffer();

        String errType = PropertiesHandler.getOptionalProperty(PropertiesConstants.TASK_OUTPUT_TYPE);
        String outType = PropertiesHandler.getOptionalProperty(PropertiesConstants.SQL_OUTPUT_TYPE);

        if (errType.equals(FormNode.OUTPUT_TO_FILE)) errStream = new StatusFileOutput(PropertiesHandler.getRequiredProperty(PropertiesConstants.TASK_OUTPUT_LOCATION));
        else errStream = new StatusConsoleOutput(textArea);
        if (outType.equals(FormNode.OUTPUT_TO_FILE)) outStream = new StatusFileOutput(PropertiesHandler.getRequiredProperty(PropertiesConstants.SQL_OUTPUT_LOCATION));
        else outStream = new StatusConsoleOutput(textArea);

        open();

        System.setErr(new PrintStream(errStream));
        //System.setOut(new PrintStream(outStream));

        printToOutputStream(currentOutPrint);
    }

    public void printToOutputStream(boolean printOutput) {
        outStream.setPrintToBuffer(printOutput);
    }

    public void open() {
        if (errStream != null) errStream.openStream();
        if (outStream != null) outStream.openStream();
    }

    public void flush() {
        if (errStream != null) errStream.flushStream();
        if (outStream != null) outStream.flushStream();
    }

    public void close() {
        if (errStream != null) errStream.closeStream();
        if (outStream != null) outStream.closeStream();
    }

    public void clear() {
        if (errStream != null) errStream.clearStream();
        if (outStream != null) outStream.clearStream();
    }
}
