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
    @Column(name = "hole_type")
    private String holeType;
    @Convert(converter = HoleClosedTypeConverter.class)
    @Column(name = "hole_closed_type")
    private HoleClosedType holeClosedType;
    @Convert(converter = HoleClosedLocationConverter.class)
    @Column(name = "hole_closed_location")
    private HoleClosedLocation holeClosedLocation;
    @Column(name = "hole_shape")
    private String holeShape;

    public Hole() {

    }

    public String getHoleType() {
        return holeType;
    }

    public void setHoleType(String holeType) {
        this.holeType = holeType;
    }

    public HoleClosedType getHoleClosedType() {
        return holeClosedType;
    }

    public void setHoleClosedType(HoleClosedType holeClosedType) {
        this.holeClosedType = holeClosedType;
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
