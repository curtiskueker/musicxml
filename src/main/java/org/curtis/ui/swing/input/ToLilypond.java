package org.curtis.ui.swing.input;

public class ToLilypond extends ToInput {
    public ToLilypond() {
        setup();
    }

    private void setup() {
        setTitle("Lilypond File Output: ");

        getInputRows().add(InputRowFactory.newDirectory("outputDirectory", "Output Directory: "));
        getInputRows().add(InputRowFactory.newInput("outputFile", "Output Filename (.ly): ", InputRow.SMALL_INPUT_SIZE));
        getInputRows().add(InputRowFactory.newCheckbox("includeBreaks", "Include Page and System Breaks: "));
    }
}
