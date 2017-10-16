package org.curtis.musicxml.direction.type;

import org.curtis.musicxml.note.TimeModification;

public class MetronomeTuplet {
    private TimeModification timeModification;
    private String type;
    private Boolean bracket;
    private String showNumber;

    public MetronomeTuplet() {

    }

    public TimeModification getTimeModification() {
        return timeModification;
    }

    public void setTimeModification(TimeModification timeModification) {
        this.timeModification = timeModification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getBracket() {
        return bracket;
    }

    public void setBracket(Boolean bracket) {
        this.bracket = bracket;
    }

    public String getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(String showNumber) {
        this.showNumber = showNumber;
    }
}
