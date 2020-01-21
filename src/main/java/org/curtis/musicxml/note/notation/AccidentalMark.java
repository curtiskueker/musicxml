package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.LevelDisplay;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.note.AccidentalType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("accidental mark")
public class AccidentalMark extends Notation {
    @Enumerated(EnumType.STRING)
    @Column(name = "accidental_type")
    private AccidentalType accidentalType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_display_id")
    private LevelDisplay levelDisplay;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Column
    private String smufl;

    public AccidentalMark() {

    }

    public AccidentalType getAccidentalType() {
        return accidentalType;
    }

    public void setAccidentalType(AccidentalType accidentalType) {
        this.accidentalType = accidentalType;
    }

    public LevelDisplay getLevelDisplay() {
        return levelDisplay;
    }

    public void setLevelDisplay(LevelDisplay levelDisplay) {
        this.levelDisplay = levelDisplay;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
