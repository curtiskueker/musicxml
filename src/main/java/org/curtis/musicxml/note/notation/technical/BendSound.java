package org.curtis.musicxml.note.notation.technical;

import java.math.BigDecimal;

public class BendSound {
    private Boolean accelerate;
    private BigDecimal beats;
    private BigDecimal firstBeat;
    private BigDecimal lastBeat;

    public BendSound() {

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

    public BigDecimal getFirstBeat() {
        return firstBeat;
    }

    public void setFirstBeat(BigDecimal firstBeat) {
        this.firstBeat = firstBeat;
    }

    public BigDecimal getLastBeat() {
        return lastBeat;
    }

    public void setLastBeat(BigDecimal lastBeat) {
        this.lastBeat = lastBeat;
    }
}
