package org.curtis.ui.swing.input;

import org.curtis.musicxml.util.MusicXmlUtil;

public class FromDatabase extends FromInput {
    public FromDatabase() {
        setup();
    }

    private void setup() {
        setTitle("Read From Database: ");

        getInputRows().add(InputRowFactory.newSelection("scoreName", "Score Name: ", MusicXmlUtil.getScoreNames()));
    }
}
