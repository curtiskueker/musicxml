package org.curtis.ui.swing.input;

import org.curtis.musicxml.util.MusicXmlUtil;

public class FromDatabase extends FromInput {
    public FromDatabase() {
        setup();
    }

    private void setup() {
        setTitle("Read From Database: ");

        InputRow inputRow1 = new InputRow();
        inputRow1.setText("Score Name: ");
        inputRow1.setInputType(InputType.SELECTION);
        inputRow1.setSelectionList(MusicXmlUtil.getScoreNames().toArray());
        inputRow1.setName("scoreName");
        getInputRows().add(inputRow1);
    }
}
