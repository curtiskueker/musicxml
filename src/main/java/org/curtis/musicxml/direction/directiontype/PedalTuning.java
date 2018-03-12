package org.curtis.musicxml.direction.directiontype;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.note.Step;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table(name = "pedal_tuning")
public class PedalTuning extends DatabaseItem {
    @Transient
    private Step pedalStep;
    @Transient
    private BigDecimal pedalAlter;

    public PedalTuning() {

    }

    public Step getPedalStep() {
        return pedalStep;
    }

    public void setPedalStep(Step pedalStep) {
        this.pedalStep = pedalStep;
    }

    public BigDecimal getPedalAlter() {
        return pedalAlter;
    }

    public void setPedalAlter(BigDecimal pedalAlter) {
        this.pedalAlter = pedalAlter;
    }
}
