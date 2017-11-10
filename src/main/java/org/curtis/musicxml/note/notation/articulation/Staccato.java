package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.Placement;

public class Staccato extends Articulation {
    private Placement placement;

    public Staccato() {

    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }
}
