package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.PlacementText;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("other technical")
public class OtherTechnical extends Technical {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "placement_text_id")
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
