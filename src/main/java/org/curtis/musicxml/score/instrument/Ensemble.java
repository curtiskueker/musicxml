package org.curtis.musicxml.score.instrument;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("ensemble")
public class Ensemble extends InstrumentType {
    @Transient
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
