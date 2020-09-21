package org.curtis.musicxml.note.notation.ornament;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.StartNoteConverter;
import org.curtis.musicxml.converter.TrillStepConverter;
import org.curtis.musicxml.converter.TwoNoteTurnConverter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "trill_sound")
public class TrillSound extends DatabaseItem {
    @Convert(converter = StartNoteConverter.class)
    @Column(name = "start_note")
    private StartNote startNote;
    @Convert(converter = TrillStepConverter.class)
    @Column(name = "trill_step")
    private TrillStep trillStep;
    @Convert(converter = TwoNoteTurnConverter.class)
    @Column(name = "two_note_turn")
    private TwoNoteTurn twoNoteTurn;
    @Column
    @Type(type="yes_no")
    private Boolean accelerate;
    @Column(precision = 12, scale = 4)
    private BigDecimal beats;
    @Column(name = "second_beat", precision = 12, scale = 4)
    private BigDecimal secondBeat;
    @Column(name = "last_beat", precision = 12, scale = 4)
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
