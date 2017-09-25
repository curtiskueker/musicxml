package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.EmptyLine;

public class Plop extends Articulation {
    private EmptyLine value;

    public Plop() {

    }

    public EmptyLine getValue() {
        return value;
    }

    public void setValue(EmptyLine value) {
        this.value = value;
    }
}
