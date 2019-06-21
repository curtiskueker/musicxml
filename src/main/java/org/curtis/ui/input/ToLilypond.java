package org.curtis.ui.input;

public class ToLilypond extends ToInput {
    public ToLilypond() {
        setup();
    }

    private void setup() {
        setTitle("Lilypond File Output: ");

        InputRow inputRow1 = new InputRow();
        inputRow1.setText("Output Directory: ");
        inputRow1.setInputType(InputType.OUTPUT_DIRECTORY);
        inputRow1.setName("outputDirectory");
        getInputRows().add(inputRow1);

        InputRow inputRow2 = new InputRow();
        inputRow2.setText("Output Filename (.ly): ");
        inputRow2.setInputType(InputType.INPUT);
        inputRow2.setInputSize(InputRow.SMALL_INPUT_SIZE);
        inputRow2.setName("outputFile");
        getInputRows().add(inputRow2);

        InputRow inputRow3 = new InputRow();
        inputRow3.setText("Include Page and System Breaks: ");
        inputRow3.setInputType(InputType.CHECKBOX);
        inputRow3.setName("includeBreaks");
        getInputRows().add(inputRow3);
    }
}
