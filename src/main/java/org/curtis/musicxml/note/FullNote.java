package org.curtis.musicxml.note;

public class FullNote {
    private Boolean chord;
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
