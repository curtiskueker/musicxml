package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.PlacementText;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("other articulation")
public class OtherArticulation extends Articulation {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "placement_text_id")
    private PlacementText placementText;

    public OtherArticulation() {

    }

    public PlacementText getPlacementText() {
        return placementText;
    }

    public void setPlacementText(PlacementText placementText) {
        this.placementText = placementText;
    }
}
