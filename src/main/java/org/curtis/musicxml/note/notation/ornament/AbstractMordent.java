package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.display.Placement;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractMordent extends PlacedTrillSound {
    @Column(name = "long_mordent")
    @Type(type="yes_no")
    private Boolean longMordent;
    @Enumerated(EnumType.STRING)
    @Column
    private Placement approach;
    @Enumerated(EnumType.STRING)
    @Column
    private Placement departure;

    public Boolean getLongMordent() {
        return longMordent;
    }

    public void setLongMordent(Boolean longMordent) {
        this.longMordent = longMordent;
    }

    public Placement getApproach() {
        return approach;
    }

    public void setApproach(Placement approach) {
        this.approach = approach;
    }

    public Placement getDeparture() {
        return departure;
    }

    public void setDeparture(Placement departure) {
        this.departure = departure;
    }
}
