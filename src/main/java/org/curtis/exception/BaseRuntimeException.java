package org.curtis.exception;

public class BaseRuntimeException extends RuntimeException {
    public BaseRuntimeException(String message){
        super(message);
    }

    public BaseRuntimeException(Exception e){
        super(e);
    }
}
