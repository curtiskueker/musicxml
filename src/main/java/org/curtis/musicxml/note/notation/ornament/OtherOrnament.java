package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.note.PlacementText;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("other ornament")
public class OtherOrnament extends Ornament {
    @Transient
    private PlacementText placementText;

    public OtherOrnament() {

    }

    public PlacementText getPlacementText() {
        return placementText;
    }

    public void setPlacementText(PlacementText placementText) {
        this.placementText = placementText;
    }
}
