package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.note.Placement;

public class Schleifer extends Ornament {
    private Placement value;

    public Schleifer() {

    }

    public Placement getValue() {
        return value;
    }

    public void setValue(Placement value) {
        this.value = value;
    }
}
