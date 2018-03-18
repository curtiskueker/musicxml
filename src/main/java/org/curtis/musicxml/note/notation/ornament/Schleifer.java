package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.note.Placement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("schleifer")
public class Schleifer extends Ornament {
    @Transient
    private Placement placement;

    public Schleifer() {

    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }
}
