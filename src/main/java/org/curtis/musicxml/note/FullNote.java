package org.curtis.musicxml.note;

public class FullNote {
    // TODO: chord
    private Pitch pitch;
    private Unpitched unpitched;
    private Rest rest;

    public FullNote() {

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
