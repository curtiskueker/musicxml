package org.curtis.musicxml.direction.directiontype;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.attributes.Tuning;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accord")
public class Accord extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tuning_id")
    private Tuning tuning;
    @Column
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
