package org.curtis.musicxml.attributes;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.note.Step;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "tuning")
public class Tuning extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "tuning_step")
    private Step tuningStep;
    @Column(name = "tuning_alter")
    private BigDecimal tuningAlter;
    @Column(name = "tuning_octave")
    private Integer tuningOctave;

    public Tuning() {

    }

    public Step getTuningStep() {
        return tuningStep;
    }

    public void setTuningStep(Step tuningStep) {
        this.tuningStep = tuningStep;
    }

    public BigDecimal getTuningAlter() {
        return tuningAlter;
    }

    public void setTuningAlter(BigDecimal tuningAlter) {
        this.tuningAlter = tuningAlter;
    }

    public Integer getTuningOctave() {
        return tuningOctave;
    }

    public void setTuningOctave(Integer tuningOctave) {
        this.tuningOctave = tuningOctave;
    }
}
