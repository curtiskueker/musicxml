package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

public class Hole extends Technical {
    private String holeType;
    private HoleClosedType holeClosedType;
    private HoleClosedLocation holeClosedLocation;
    private String holeShape;
    private PrintStyle printStyle;
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
