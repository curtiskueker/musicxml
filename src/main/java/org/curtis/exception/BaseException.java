package org.curtis.exception;

public class BaseException extends Exception {
    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(Exception e) {
        super(e);
    }
}
