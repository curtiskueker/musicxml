package org.curtis.ui.task;

import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.ui.task.exception.TaskException;

public abstract class MusicXmlTask {
    protected TaskInitializer taskInitializer;

    static {
        MusicXmlUtil.DEBUG = true;
    }

    protected MusicXmlTask(TaskInitializer taskInitializer) {
        this.taskInitializer = taskInitializer;
    }

    public void execute() throws TaskException {
        initialize();
        executeTask();
    }

    public abstract void initialize();
    public abstract void executeTask() throws TaskException;
}
