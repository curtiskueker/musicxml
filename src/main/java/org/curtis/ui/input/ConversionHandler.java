package org.curtis.ui.input;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.ui.input.conversion.ConversionInput;
import org.curtis.ui.task.TaskInitializer;

public abstract class ConversionHandler extends InputHandler {
    protected MusicXmlScript musicXmlScript;
    protected ConversionInput fromInput;
    protected ConversionInput toInput;

    public ConversionHandler() {

    }

    @Override
    public MusicXmlScript transferInputToTask(TaskInitializer taskInitializer) {
        fromInput.convertInput(musicXmlScript, taskInitializer);
        toInput.convertInput(musicXmlScript, taskInitializer);

        return musicXmlScript;
    }
}
