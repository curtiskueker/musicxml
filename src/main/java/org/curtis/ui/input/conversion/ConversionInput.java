package org.curtis.ui.input.conversion;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.ui.task.TaskInitializer;

public abstract class ConversionInput {
    public abstract void convertInput(MusicXmlScript musicXmlScript, TaskInitializer taskInitializer);
}
