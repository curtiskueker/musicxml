package org.curtis.musicxml.note;

import org.curtis.musicxml.common.Connection;

public class Tie {
    private Connection type;
    private String timeOnly;

    public Tie() {

    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public String getTimeOnly() {
        return timeOnly;
    }

    public void setTimeOnly(String timeOnly) {
        this.timeOnly = timeOnly;
    }
}
