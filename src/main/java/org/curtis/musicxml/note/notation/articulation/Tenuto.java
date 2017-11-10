package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.Placement;

public class Tenuto extends Articulation {
    private Placement placement;

    public Tenuto() {

    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }
}
