package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Connection;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Tie extends DatabaseItem {
    @Transient
    private Connection type;
    @Transient
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
