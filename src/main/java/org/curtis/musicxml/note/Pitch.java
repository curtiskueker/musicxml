package org.curtis.musicxml.note;

import java.math.BigDecimal;

public class Pitch extends FullNoteType {
    private Step step;
    private BigDecimal alter;
    private Integer octave;

    public Pitch() {

    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public BigDecimal getAlter() {
        return alter;
    }

    public void setAlter(BigDecimal alter) {
        this.alter = alter;
    }

    public Integer getOctave() {
        return octave;
    }

    public void setOctave(Integer octave) {
        this.octave = octave;
    }
}
