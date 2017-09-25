package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.note.EmptyPlacement;

public class Schleifer extends Ornament {
    private EmptyPlacement value;

    public Schleifer() {

    }

    public EmptyPlacement getValue() {
        return value;
    }

    public void setValue(EmptyPlacement value) {
        this.value = value;
    }
}
