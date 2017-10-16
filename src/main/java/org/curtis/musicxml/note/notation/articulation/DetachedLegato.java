package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.Placement;

public class DetachedLegato extends Articulation {
    private Placement value;

    public DetachedLegato() {

    }

    public Placement getValue() {
        return value;
    }

    public void setValue(Placement value) {
        this.value = value;
    }
}
