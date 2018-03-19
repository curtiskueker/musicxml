package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.PlacementText;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("other technical")
public class OtherTechnical extends Technical {
    @Transient
    private PlacementText placementText;

    public OtherTechnical() {

    }

    public PlacementText getPlacementText() {
        return placementText;
    }

    public void setPlacementText(PlacementText placementText) {
        this.placementText = placementText;
    }
}
