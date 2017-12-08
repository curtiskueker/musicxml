package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.note.Step;

import java.math.BigDecimal;

public class PedalTuning {
    private Step pedalStep;
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
