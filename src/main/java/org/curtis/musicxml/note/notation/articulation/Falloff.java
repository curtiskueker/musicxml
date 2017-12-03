package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.Line;

public class Falloff extends Articulation {
    private Line line;

    public Falloff() {

    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }
}
