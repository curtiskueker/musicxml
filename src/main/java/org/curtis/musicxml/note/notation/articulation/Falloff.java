package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.Line;

public class Falloff extends Articulation {
    private Line value;

    public Falloff() {

    }

    public Line getValue() {
        return value;
    }

    public void setValue(Line value) {
        this.value = value;
    }
}
