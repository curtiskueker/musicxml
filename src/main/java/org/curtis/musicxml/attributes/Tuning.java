package org.curtis.musicxml.attributes;

import org.curtis.musicxml.note.Step;

import java.math.BigDecimal;

public class Tuning {
    private Step tuningStep;
    private BigDecimal tuningAlter;
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
