package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.EmptyLine;

public class Falloff extends Articulation {
    private EmptyLine value;

    public Falloff() {

    }

    public EmptyLine getValue() {
        return value;
    }

    public void setValue(EmptyLine value) {
        this.value = value;
    }
}
