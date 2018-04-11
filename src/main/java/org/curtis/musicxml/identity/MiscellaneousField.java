package org.curtis.musicxml.identity;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "miscellaneous_field")
public class MiscellaneousField extends DatabaseItem {
    @Column
    private String value;
    @Column
    private String name;

    public MiscellaneousField() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
