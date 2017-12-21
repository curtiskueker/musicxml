package org.curtis.lilypond.exception;

public class BuildException extends LilypondException {
    public BuildException(String message) {
        super(message);
    }

    public BuildException(Exception e) {
        super(e);
    }
}
