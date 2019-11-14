package org.curtis.ui.swing.input;

public class FromLilypond extends FromInput {
    public FromLilypond() {
        setup();
    }

    private void setup() {
        setTitle("Lilypond File Input: ");

        getInputRows().add(InputRowFactory.newInputFile("inputFile", "Input File: ", "ly"));
    }
}
