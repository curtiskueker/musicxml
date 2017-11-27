package org.curtis.musicxml.note;

import org.curtis.musicxml.common.Connection;

public abstract class FullNote {
    private Boolean chord = false;
    private Connection chordType;

    public Boolean getChord() {
        return chord;
    }

    public void setChord(Boolean chord) {
        this.chord = chord;
    }

    public Connection getChordType() {
        return chordType;
    }

    public void setChordType(Connection chordType) {
        this.chordType = chordType;
    }
}
