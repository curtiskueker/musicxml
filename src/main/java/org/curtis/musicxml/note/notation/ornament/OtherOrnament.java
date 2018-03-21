package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.note.PlacementText;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("other ornament")
public class OtherOrnament extends Ornament {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "placement_text_id")
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
