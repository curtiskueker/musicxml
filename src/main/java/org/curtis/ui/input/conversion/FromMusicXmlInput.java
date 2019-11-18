package org.curtis.ui.input.conversion;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.ui.task.TaskInitializer;

public class FromMusicXmlInput extends ConversionInput {
    public FromMusicXmlInput() {

    }

    @Override
    public void convertInput(MusicXmlScript musicXmlScript, TaskInitializer taskInitializer) {
        musicXmlScript.setInputFile(taskInitializer.getDirectoryLocation("inputFile"));
    }
}
