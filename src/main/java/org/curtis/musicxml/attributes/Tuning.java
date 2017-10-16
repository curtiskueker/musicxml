package org.curtis.musicxml.attributes;

import java.math.BigDecimal;

public class Tuning {
    private String tuningStep;
    private BigDecimal tuningAlter;
    private Integer tuningOctave;

    public Tuning() {

    }

    public String getTuningStep() {
        return tuningStep;
    }

    public void setTuningStep(String tuningStep) {
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
