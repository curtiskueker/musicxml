package org.curtis.musicxml.note.notation.technical;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "harmon_closed")
public class HarmonClosed extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column
    private HarmonClosedValue value;
    @Enumerated(EnumType.STRING)
    @Column
    private HarmonClosedLocation location;

    public HarmonClosed() {

    }

    public HarmonClosedValue getValue() {
        return value;
    }

    public void setValue(HarmonClosedValue value) {
        this.value = value;
    }

    public HarmonClosedLocation getLocation() {
        return location;
    }

    public void setLocation(HarmonClosedLocation location) {
        this.location = location;
    }
}
