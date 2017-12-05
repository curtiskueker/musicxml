package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.common.Location;

public abstract class AbstractMordent extends PlacedTrillSound {
    private Boolean longMordent;
    private Location approach;
    private Location departure;

    public Boolean getLongMordent() {
        return longMordent;
    }

    public void setLongMordent(Boolean longMordent) {
        this.longMordent = longMordent;
    }

    public Location getApproach() {
        return approach;
    }

    public void setApproach(Location approach) {
        this.approach = approach;
    }

    public Location getDeparture() {
        return departure;
    }

    public void setDeparture(Location departure) {
        this.departure = departure;
    }
}
