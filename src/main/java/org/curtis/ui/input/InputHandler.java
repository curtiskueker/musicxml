package org.curtis.ui.input;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.ui.task.TaskInitializer;

public abstract class InputHandler {
    public abstract MusicXmlScript transferInputToTask(TaskInitializer taskInitializer);
}
