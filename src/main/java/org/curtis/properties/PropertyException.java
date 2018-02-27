package org.curtis.properties;

import org.curtis.exception.BaseException;

public class PropertyException extends BaseException {
    public PropertyException(String message){
        super(message);
    }

    public PropertyException(Exception e){
        super(e);
    }
}
