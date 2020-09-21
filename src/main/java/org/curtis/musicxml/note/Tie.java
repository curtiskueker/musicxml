package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.database.OrderedItem;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.converter.ConnectionConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tie")
public class Tie extends OrderedItem {
    @Convert(converter = ConnectionConverter.class)
    @Column
    private Connection type;
    @Column(name = "time_only")
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
