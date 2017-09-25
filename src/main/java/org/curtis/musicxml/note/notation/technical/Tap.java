package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.PlacementText;

public class Tap extends Technical {
    private PlacementText value;

    public Tap() {

    }

    public PlacementText getValue() {
        return value;
    }

    public void setValue(PlacementText value) {
        this.value = value;
    }
}
