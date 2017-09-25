package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.EmptyPlacement;

public class Accent extends Articulation {
    private EmptyPlacement value;

    public Accent() {

    }

    public EmptyPlacement getValue() {
        return value;
    }

    public void setValue(EmptyPlacement value) {
        this.value = value;
    }
}
