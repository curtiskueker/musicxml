package org.curtis.ui.task;

import org.curtis.musicxml.bin.MusicXmlScript;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.ui.input.InputHandler;
import org.curtis.ui.task.exception.TaskException;

public abstract class MusicXmlTask {
    protected TaskInitializer taskInitializer;
    protected InputHandler inputHandler;

    static {
        MusicXmlUtil.DEBUG = true;
    }

    protected MusicXmlTask(TaskInitializer taskInitializer, InputHandler inputHandler) {
        this.taskInitializer = taskInitializer;
        this.inputHandler = inputHandler;
    }

    public void execute() throws TaskException {
        MusicXmlScript musicXmlScript = inputHandler.transferInputToTask(taskInitializer);

        try {
            musicXmlScript.execute();
        } catch (MusicXmlException e) {
            throw new TaskException(e.getMessage());
        }
    }
}
