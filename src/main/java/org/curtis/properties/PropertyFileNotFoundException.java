package org.curtis.properties;

import org.curtis.exception.BaseRuntimeException;

public class PropertyFileNotFoundException extends BaseRuntimeException {
    public PropertyFileNotFoundException(String message){
        super(message);
    }

    public PropertyFileNotFoundException(Exception e){
        super(e);
    }
}
