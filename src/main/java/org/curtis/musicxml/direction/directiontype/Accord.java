package org.curtis.musicxml.direction.directiontype;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.attributes.Tuning;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "accord")
public class Accord extends DatabaseItem {
    @Transient
    private Tuning tuning;
    @Transient
    private Integer string;

    public Accord() {

    }

    public Tuning getTuning() {
        return tuning;
    }

    public void setTuning(Tuning tuning) {
        this.tuning = tuning;
    }

    public Integer getString() {
        return string;
    }

    public void setString(Integer string) {
        this.string = string;
    }
}
