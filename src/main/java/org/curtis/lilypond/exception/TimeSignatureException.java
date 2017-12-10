package org.curtis.lilypond.exception;

public class TimeSignatureException extends LilypondException {
    public TimeSignatureException(String message) {
        super(message);
    }

    public TimeSignatureException(Exception e) {
        super(e);
    }
}
