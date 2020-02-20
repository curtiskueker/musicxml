package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.note.PlacementText;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("other ornament")
public class OtherOrnament extends Ornament {
    @Column
    private String value;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "placement_text_id")
    private PlacementText placementText;
    @Column
    private String smufl;

    public OtherOrnament() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PlacementText getPlacementText() {
        return placementText;
    }

    public void setPlacementText(PlacementText placementText) {
        this.placementText = placementText;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
