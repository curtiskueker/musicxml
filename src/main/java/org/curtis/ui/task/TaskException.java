package org.curtis.ui.task;

import org.curtis.exception.BaseException;

public class TaskException extends BaseException {
    public TaskException(String message) {
        super(message);
    }

    public TaskException(Exception e) {
        super(e);
    }
}
