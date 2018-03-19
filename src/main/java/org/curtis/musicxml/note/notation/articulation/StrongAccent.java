package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.note.Placement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("strong accent")
public class StrongAccent extends Articulation {
    @Transient
    private Placement placement;
    @Transient
    private Location type = Location.UP;

    public StrongAccent() {

    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    public Location getType() {
        return type;
    }

    public void setType(Location type) {
        this.type = type;
    }
}
