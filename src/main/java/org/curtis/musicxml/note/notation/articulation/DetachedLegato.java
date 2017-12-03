package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.Placement;

public class DetachedLegato extends Articulation {
    private Placement placement;

    public DetachedLegato() {

    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }
}
