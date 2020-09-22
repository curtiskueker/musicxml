package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.converter.HoleClosedLocationConverter;
import org.curtis.musicxml.converter.HoleClosedTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("hole")
public class Hole extends Technical {
    @Column
    private String type;
    @Convert(converter = HoleClosedTypeConverter.class)
    @Column(name = "hole_closed")
    private HoleClosedType holeClosed;
    @Convert(converter = HoleClosedLocationConverter.class)
    @Column(name = "location")
    private HoleClosedLocation holeClosedLocation;
    @Column(name = "hole_shape")
    private String holeShape;

    public Hole() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HoleClosedType getHoleClosed() {
        return holeClosed;
    }

    public void setHoleClosed(HoleClosedType holeClosed) {
        this.holeClosed = holeClosed;
    }

    public HoleClosedLocation getHoleClosedLocation() {
        return holeClosedLocation;
    }

    public void setHoleClosedLocation(HoleClosedLocation holeClosedLocation) {
        this.holeClosedLocation = holeClosedLocation;
    }

    public String getHoleShape() {
        return holeShape;
    }

    public void setHoleShape(String holeShape) {
        this.holeShape = holeShape;
    }
}
