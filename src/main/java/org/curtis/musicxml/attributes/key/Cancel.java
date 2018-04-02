package org.curtis.musicxml.attributes.key;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "cancel")
public class Cancel extends DatabaseItem {
    @Column
    private Integer fifths;
    @Enumerated(EnumType.STRING)
    @Column
    private CancelLocation location;

    public Cancel() {

    }

    public Integer getFifths() {
        return fifths;
    }

    public void setFifths(Integer fifths) {
        this.fifths = fifths;
    }

    public CancelLocation getLocation() {
        return location;
    }

    public void setLocation(CancelLocation location) {
        this.location = location;
    }
}
