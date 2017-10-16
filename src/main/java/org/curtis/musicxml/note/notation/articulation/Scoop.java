package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.Line;

public class Scoop extends Articulation {
    private Line value;

    public Scoop() {

    }

    public Line getValue() {
        return value;
    }

    public void setValue(Line value) {
        this.value = value;
    }
}
