package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.PrintStyle;

public class Hole extends Technical {
    private String holeType;
    private String holeClosedValue;
    private String holeClosedLocation;
    private String holeShape;
    private PrintStyle printStyle;
    private String placement;

    public Hole() {

    }

    public String getHoleType() {
        return holeType;
    }

    public void setHoleType(String holeType) {
        this.holeType = holeType;
    }

    public String getHoleClosedValue() {
        return holeClosedValue;
    }

    public void setHoleClosedValue(String holeClosedValue) {
        this.holeClosedValue = holeClosedValue;
    }

    public String getHoleClosedLocation() {
        return holeClosedLocation;
    }

    public void setHoleClosedLocation(String holeClosedLocation) {
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

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}
