package org.curtis.ui.input;

public class FromLilypond extends FromInput {
    public FromLilypond() {
        setup();
    }

    private void setup() {
        setTitle("Lilypond File Input: ");

        InputRow inputRow1 = new InputRow();
        inputRow1.setText("Input File: ");
        inputRow1.setInputType(InputType.INPUT_FILE);
        inputRow1.setName("inputFile");
        inputRow1.setValue("ly");
        getInputRows().add(inputRow1);
    }
}
