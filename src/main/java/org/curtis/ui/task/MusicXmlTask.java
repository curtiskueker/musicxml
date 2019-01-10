package org.curtis.ui.task;

import org.curtis.ui.task.exception.TaskException;

import java.awt.*;
import java.util.Map;

public abstract class MusicXmlTask {
    protected Map<String, Component> componentMap;

    protected MusicXmlTask(Map<String, Component> componentMap) {
        this.componentMap = componentMap;
    }

    public abstract void execute() throws TaskException;
}
