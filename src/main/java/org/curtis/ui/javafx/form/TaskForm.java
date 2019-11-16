package org.curtis.ui.javafx.form;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.curtis.ui.javafx.StatusOutput;

import java.io.PrintStream;

public class TaskForm {
    // The outermost Container
    private VBox taskBox;
    private StatusOutput statusOutput;
    private TextArea statusTextArea;

    private Thread outputThread;

    public TaskForm() {

    }

    public void setTaskBox(VBox taskBox) {
        this.taskBox = taskBox;
    }

    public void setStatusTextArea(TextArea statusTextArea) {
        this.statusTextArea = statusTextArea;
    }

    public void initialize() {
        statusOutput = new StatusOutput(statusTextArea);
        PrintStream statusPrintStream = new PrintStream(statusOutput);
        System.setErr(statusPrintStream);
        System.setOut(statusPrintStream);

        Runnable outputRunnable = statusOutput::handle;
        outputThread = new Thread(outputRunnable);
        outputThread.start();
    }

    public void cleanup() {
        if (outputThread != null) outputThread.interrupt();
    }

    public void clearOutput() {
        statusOutput.clear();
    }
}
