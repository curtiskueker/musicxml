package org.curtis.ui.swing.input;

public class FromMusicXml extends FromInput {
    public FromMusicXml() {
        setup();
    }

    private void setup() {
        setTitle("MusicXml File Input: ");

        getInputRows().add(InputRowFactory.newInputFile("inputFile", "Input File: ", "xml"));
    }
}
