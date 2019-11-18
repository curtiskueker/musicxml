package org.curtis.ui.input.conversion;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.ui.task.TaskInitializer;

public class FromDbInput extends ConversionInput {
    public FromDbInput() {

    }

    @Override
    public void convertInput(MusicXmlScript musicXmlScript, TaskInitializer taskInitializer) {
        musicXmlScript.setScoreName(taskInitializer.getSelection("scoreName"));
    }
}
