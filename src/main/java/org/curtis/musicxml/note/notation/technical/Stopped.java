package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.EmptyPlacement;

public class Stopped extends Technical {
    private EmptyPlacement value;

    public Stopped() {

    }

    public EmptyPlacement getValue() {
        return value;
    }

    public void setValue(EmptyPlacement value) {
        this.value = value;
    }
}
