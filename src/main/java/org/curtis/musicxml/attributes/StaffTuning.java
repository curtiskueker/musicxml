package org.curtis.musicxml.attributes;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "staff_tuning")
public class StaffTuning extends DatabaseItem {
    @Transient
    private Tuning tuning;
    @Column
    private Integer line;

    public StaffTuning() {

    }

    public Tuning getTuning() {
        return tuning;
    }

    public void setTuning(Tuning tuning) {
        this.tuning = tuning;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }
}
