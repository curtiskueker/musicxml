package org.curtis.database;

import org.curtis.exception.BaseException;

public class DBException extends BaseException {
    public DBException(String msg) {
        super(msg);
    }

    public DBException(Exception e) {
        super(e);
    }
}
