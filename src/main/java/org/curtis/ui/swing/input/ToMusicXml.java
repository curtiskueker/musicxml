package org.curtis.ui.swing.input;

public class ToMusicXml extends ToInput {
    public ToMusicXml() {
        setup();
    }

    private void setup() {
        setTitle("MusicXml File Output: ");

        InputRow inputRow1 = new InputRow();
        inputRow1.setText("Output Directory: ");
        inputRow1.setInputType(InputType.OUTPUT_DIRECTORY);
        inputRow1.setName("outputDirectory");
        getInputRows().add(inputRow1);

        InputRow inputRow2 = new InputRow();
        inputRow2.setText("Output Filename (.xml): ");
        inputRow2.setInputType(InputType.INPUT);
        inputRow2.setInputSize(InputRow.SMALL_INPUT_SIZE);
        inputRow2.setName("outputFile");
        getInputRows().add(inputRow2);

        InputRow inputRow3 = new InputRow();
        inputRow3.setText("Skip Comments: ");
        inputRow3.setInputType(InputType.CHECKBOX);
        inputRow3.setName("skipComments");
        getInputRows().add(inputRow3);
    }
}
