package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.EmptyLine;

public class Scoop extends Articulation {
    private EmptyLine value;

    public Scoop() {

    }

    public EmptyLine getValue() {
        return value;
    }

    public void setValue(EmptyLine value) {
        this.value = value;
    }
}
