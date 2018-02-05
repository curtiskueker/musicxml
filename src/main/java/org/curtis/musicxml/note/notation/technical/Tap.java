package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.PlacementText;

public class Tap extends Technical {
    private PlacementText placementText;

    public Tap() {

    }

    public PlacementText getPlacementText() {
        return placementText;
    }

    public void setPlacementText(PlacementText placementText) {
        this.placementText = placementText;
    }
}
