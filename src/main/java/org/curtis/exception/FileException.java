package org.curtis.exception;

public class FileException extends BaseException {
    public FileException(String message) {
        super(message);
    }

    public FileException(Exception e) {
        super(e);
    }
}
