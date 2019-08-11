package org.curtis.ui.swing.input;

public class FromMusicXml extends FromInput {
    public FromMusicXml() {
        setup();
    }

    private void setup() {
        setTitle("MusicXml File Input: ");

        InputRow inputRow1 = new InputRow();
        inputRow1.setText("Input File: ");
        inputRow1.setInputType(InputType.INPUT_FILE);
        inputRow1.setName("inputFile");
        inputRow1.setValue("xml");
        getInputRows().add(inputRow1);
    }
}
