package org.curtis.properties;

import org.curtis.exception.BaseRuntimeException;

public class RequiredPropertyNotFoundException extends BaseRuntimeException {
    public RequiredPropertyNotFoundException(String message){
        super(message);
    }

    public RequiredPropertyNotFoundException(Exception e){
        super(e);
    }
}
