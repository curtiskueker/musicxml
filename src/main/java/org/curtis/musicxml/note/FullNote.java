package org.curtis.musicxml.note;

public abstract class FullNote {
    private Boolean chord = false;
    private boolean isBeginChord;
    private boolean isContinueChord;
    private boolean isEndChord;

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
}
