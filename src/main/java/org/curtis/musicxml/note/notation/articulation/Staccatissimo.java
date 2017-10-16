package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.Placement;

public class Staccatissimo extends Articulation {
    private Placement value;

    public Staccatissimo() {

    }

    public Placement getValue() {
        return value;
    }

    public void setValue(Placement value) {
        this.value = value;
    }
}
