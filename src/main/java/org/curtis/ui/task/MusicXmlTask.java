package org.curtis.ui.task;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.ui.input.InputHandler;

public class MusicXmlTask {
    protected TaskInitializer taskInitializer;
    protected InputHandler inputHandler;

    static {
        MusicXmlUtil.DEBUG = true;
    }

    public MusicXmlTask(TaskInitializer taskInitializer, InputHandler inputHandler) {
        this.taskInitializer = taskInitializer;
        this.inputHandler = inputHandler;
    }

    public void execute() throws TaskException {
        MusicXmlScript musicXmlScript = inputHandler.transferInputToTask(taskInitializer);
        if (musicXmlScript == null) throw new TaskException("Task not selected");

        try {
            musicXmlScript.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e.getMessage());
        }
    }
}
