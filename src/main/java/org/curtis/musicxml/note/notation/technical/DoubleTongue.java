package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.Placement;

public class DoubleTongue extends Technical {
    private Placement value;

    public DoubleTongue() {

    }

    public Placement getValue() {
        return value;
    }

    public void setValue(Placement value) {
        this.value = value;
    }
}
