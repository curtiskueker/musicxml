package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.Placement;

public class ThumbPosition extends Technical {
    private Placement placement;

    public ThumbPosition() {

    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }
}
