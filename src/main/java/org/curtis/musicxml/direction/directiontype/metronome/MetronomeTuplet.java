package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.notation.ShowTuplet;

public class MetronomeTuplet {
    private TimeModification timeModification;
    private Connection type;
    private Boolean bracket;
    private ShowTuplet showNumber;

    public MetronomeTuplet() {

    }

    public TimeModification getTimeModification() {
        return timeModification;
    }

    public void setTimeModification(TimeModification timeModification) {
        this.timeModification = timeModification;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public Boolean getBracket() {
        return bracket;
    }

    public void setBracket(Boolean bracket) {
        this.bracket = bracket;
    }

    public ShowTuplet getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(ShowTuplet showNumber) {
        this.showNumber = showNumber;
    }
}
