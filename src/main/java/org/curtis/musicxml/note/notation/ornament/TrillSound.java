package org.curtis.musicxml.note.notation.ornament;

import java.math.BigDecimal;

public class TrillSound {
    private StartNote startNote;
    private TrillStep trillStep;
    private TwoNoteTurn twoNoteTurn;
    private Boolean accelerate;
    private BigDecimal beats;
    private BigDecimal secondBeat;
    private BigDecimal lastBeat;

    public TrillSound() {

    }

    public StartNote getStartNote() {
        return startNote;
    }

    public void setStartNote(StartNote startNote) {
        this.startNote = startNote;
    }

    public TrillStep getTrillStep() {
        return trillStep;
    }

    public void setTrillStep(TrillStep trillStep) {
        this.trillStep = trillStep;
    }

    public TwoNoteTurn getTwoNoteTurn() {
        return twoNoteTurn;
    }

    public void setTwoNoteTurn(TwoNoteTurn twoNoteTurn) {
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
