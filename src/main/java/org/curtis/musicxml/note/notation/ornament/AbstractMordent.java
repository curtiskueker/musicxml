package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.common.Location;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class AbstractMordent extends PlacedTrillSound {
    @Column(name = "long_mordent")
    private Boolean longMordent;
    @Transient
    private Location approach;
    @Transient
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
