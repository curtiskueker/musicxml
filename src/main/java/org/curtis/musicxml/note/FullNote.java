package org.curtis.musicxml.note;

public abstract class FullNote {
    private Boolean chord = false;

    public Boolean getChord() {
        return chord;
    }

    public void setChord(Boolean chord) {
        this.chord = chord;
    }
}
