package org.curtis.musicxml.direction.directiontype;

import org.curtis.database.OrderedItem;
import org.curtis.musicxml.converter.StepConverter;
import org.curtis.musicxml.note.Step;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "pedal_tuning")
public class PedalTuning extends OrderedItem {
    @Convert(converter = StepConverter.class)
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
