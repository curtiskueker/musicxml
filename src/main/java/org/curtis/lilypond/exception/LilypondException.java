package org.curtis.lilypond.exception;

import org.curtis.exception.BaseException;

public class LilypondException extends BaseException {
    public LilypondException(String message) {
        super(message);
    }

    public LilypondException(Exception e) {
        super(e);
    }
}
