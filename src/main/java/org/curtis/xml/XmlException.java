package org.curtis.xml;

import org.curtis.exception.BaseException;

public class XmlException extends BaseException {
    public XmlException(String message){
        super(message);
    }

    public XmlException(Exception e){
        super(e);
    }
}
