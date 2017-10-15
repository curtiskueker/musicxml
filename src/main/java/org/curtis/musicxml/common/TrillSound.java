package org.curtis.musicxml.common;

import java.math.BigDecimal;

public class TrillSound {
    private String startNote;
    private String trillStep;
    private String twoNoteTurn;
    private Boolean accelerate;
    private BigDecimal beats;
    private BigDecimal secondBeat;
    private BigDecimal lastBeat;

    public TrillSound() {

    }

    public String getStartNote() {
        return startNote;
    }

    public void setStartNote(String startNote) {
        this.startNote = startNote;
    }

    public String getTrillStep() {
        return trillStep;
    }

    public void setTrillStep(String trillStep) {
        this.trillStep = trillStep;
    }

    public String getTwoNoteTurn() {
        return twoNoteTurn;
    }

    public void setTwoNoteTurn(String twoNoteTurn) {
        this.twoNoteTurn = twoNoteTurn;
    }

    public Boolean getAccelerate() {
        return accelerate;
    }

    public void setAccelerate(Boolean accelerate) {
        this.accelerate = accelerate;
    }

    public BigDecimal getBeats() {
        return beats;
    }

    public void setBeats(BigDecimal beats) {
        this.beats = beats;
    }

    public BigDecimal getSecondBeat() {
        return secondBeat;
    }

    public void setSecondBeat(BigDecimal secondBeat) {
        this.secondBeat = secondBeat;
    }

    public BigDecimal getLastBeat() {
        return lastBeat;
    }

    public void setLastBeat(BigDecimal lastBeat) {
        this.lastBeat = lastBeat;
    }
}
