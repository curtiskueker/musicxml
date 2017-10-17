package org.curtis.musicxml.exception;

import org.curtis.exception.BaseException;

public class MusicXmlException extends BaseException {
    public MusicXmlException(String message) {
        super(message);
    }

    public MusicXmlException(Exception e) {
        super(e);
    }
}
