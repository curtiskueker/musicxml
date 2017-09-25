package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.PlacementText;

public class OtherTechnical extends Technical {
    private PlacementText value;

    public OtherTechnical() {

    }

    public PlacementText getValue() {
        return value;
    }

    public void setValue(PlacementText value) {
        this.value = value;
    }
}
