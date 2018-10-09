package org.curtis.musicxml.direction.directiontype;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.note.Step;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "pedal_tuning")
public class PedalTuning extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "pedal_step")
    private Step pedalStep;
    @Column(name = "pedal_alter", precision = 12, scale = 4)
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
