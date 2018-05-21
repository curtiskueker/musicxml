package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("hole")
public class Hole extends Technical {
    @Column(name = "hole_type")
    private String holeType;
    @Enumerated(EnumType.STRING)
    @Column(name = "hole_close_type")
    private HoleClosedType holeClosedType;
    @Enumerated(EnumType.STRING)
    @Column(name = "hole_closed_location")
    private HoleClosedLocation holeClosedLocation;
    @Column(name = "hole_shape")
    private String holeShape;
    @Transient
    private PrintStyle printStyle;
    @Enumerated(EnumType.STRING)
    @Column
    private Location placement;

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

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public Location getPlacement() {
        return placement;
    }

    public void setPlacement(Location placement) {
        this.placement = placement;
    }
}
