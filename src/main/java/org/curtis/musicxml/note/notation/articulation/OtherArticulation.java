package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.PlacementText;

public class OtherArticulation extends Articulation {
    private PlacementText value;

    public OtherArticulation() {

    }

    public PlacementText getValue() {
        return value;
    }

    public void setValue(PlacementText value) {
        this.value = value;
    }
}
