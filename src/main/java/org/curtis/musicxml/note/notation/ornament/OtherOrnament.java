package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.note.PlacementText;

public class OtherOrnament extends Ornament {
    private PlacementText value;

    public OtherOrnament() {

    }

    public PlacementText getValue() {
        return value;
    }

    public void setValue(PlacementText value) {
        this.value = value;
    }
}
