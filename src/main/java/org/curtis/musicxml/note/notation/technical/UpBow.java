package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.Placement;

public class UpBow extends Technical {
    private Placement value;

    public UpBow() {

    }

    public Placement getValue() {
        return value;
    }

    public void setValue(Placement value) {
        this.value = value;
    }
}
