package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.PlacementText;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("other articulation")
public class OtherArticulation extends Articulation {
    @Transient
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
