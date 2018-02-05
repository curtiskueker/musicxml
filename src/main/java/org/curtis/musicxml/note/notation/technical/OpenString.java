package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.Placement;

public class OpenString extends Technical {
    private Placement placement;

    public OpenString() {

    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }
}
