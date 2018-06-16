package org.curtis.musicxml.score.instrument;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ensemble")
public class Ensemble extends InstrumentType {
    @Column
    private Integer value;

    public Ensemble() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
