package org.curtis.musicxml.note;

import org.curtis.musicxml.common.Connection;

public class FullNote {
    private Boolean chord = false;
    private Connection chordType;
    private FullNoteType fullNoteType;

    public Boolean isChord() {
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

    public FullNoteType getFullNoteType() {
        return fullNoteType;
    }

    public void setFullNoteType(FullNoteType fullNoteType) {
        this.fullNoteType = fullNoteType;
    }
}
