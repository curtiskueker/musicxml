package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.note.Placement;

public class StrongAccent extends Articulation {
    private Placement value;
    private Location type = Location.UP;

    public StrongAccent() {

    }

    public Placement getValue() {
        return value;
    }

    public void setValue(Placement value) {
        this.value = value;
    }

    public Location getType() {
        return type;
    }

    public void setType(Location type) {
        this.type = type;
    }
}
