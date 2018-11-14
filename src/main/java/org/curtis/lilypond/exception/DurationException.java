package org.curtis.lilypond.exception;

public class DurationException extends BuildException {
    public DurationException(String message) {
        super(message);
    }

    public DurationException(Exception e) {
        super(e);
    }
}
