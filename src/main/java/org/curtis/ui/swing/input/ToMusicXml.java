package org.curtis.ui.swing.input;

public class ToMusicXml extends ToInput {
    public ToMusicXml() {
        setup();
    }

    private void setup() {
        setTitle("MusicXml File Output: ");

        getInputRows().add(InputRowFactory.newDirectory("outputDirectory", "Output Directory: "));
        getInputRows().add(InputRowFactory.newInput("outputFile", "Output Filename (.xml): ", InputRow.SMALL_INPUT_SIZE));
        getInputRows().add(InputRowFactory.newCheckbox("skipComments", "Skip Comments: "));
    }
}
