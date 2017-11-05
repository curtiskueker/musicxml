package org.curtis.musicxml.note;

public class FullNote {
    private Boolean chord = false;
    private boolean isBeginChord;
    private boolean isContinueChord;
    private boolean isEndChord;
    private Pitch pitch;
    private Unpitched unpitched;
    private Rest rest;

    public FullNote() {

    }

    public Boolean getChord() {
        return chord;
    }

    public void setChord(Boolean chord) {
        this.chord = chord;
    }

    public boolean isBeginChord() {
        return isBeginChord;
    }

    public void setBeginChord(boolean beginChord) {
        isBeginChord = beginChord;
    }

    public boolean isContinueChord() {
        return isContinueChord;
    }

    public void setContinueChord(boolean continueChord) {
        isContinueChord = continueChord;
    }

    public boolean isEndChord() {
        return isEndChord;
    }

    public void setEndChord(boolean endChord) {
        isEndChord = endChord;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public Unpitched getUnpitched() {
        return unpitched;
    }

    public void setUnpitched(Unpitched unpitched) {
        this.unpitched = unpitched;
    }

    public Rest getRest() {
        return rest;
    }

    public void setRest(Rest rest) {
        this.rest = rest;
    }
}
