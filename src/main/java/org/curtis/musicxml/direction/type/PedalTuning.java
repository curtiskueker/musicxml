package org.curtis.musicxml.direction.type;

import java.math.BigDecimal;

public class PedalTuning {
    private String pedalStep;
    private BigDecimal pedalAlter;

    public PedalTuning() {

    }

    public String getPedalStep() {
        return pedalStep;
    }

    public void setPedalStep(String pedalStep) {
        this.pedalStep = pedalStep;
    }

    public BigDecimal getPedalAlter() {
        return pedalAlter;
    }

    public void setPedalAlter(BigDecimal pedalAlter) {
        this.pedalAlter = pedalAlter;
    }
}
