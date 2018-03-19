package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.Placement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("staccatissimo")
public class Staccatissimo extends Articulation {
    @Transient
    private Placement placement;

    public Staccatissimo() {

    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }
}
