package org.curtis.musicxml.note.notation.ornament;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "trill_sound")
public class TrillSound extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "start_note")
    private StartNote startNote;
    @Enumerated(EnumType.STRING)
    @Column(name = "trill_step")
    private TrillStep trillStep;
    @Enumerated(EnumType.STRING)
    @Column(name = "two_note_turn")
    private TwoNoteTurn twoNoteTurn;
    @Column
    private Boolean accelerate;
    @Column
    private BigDecimal beats;
    @Column(name = "second_beat")
    private BigDecimal secondBeat;
    @Column(name = "last_beat")
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
