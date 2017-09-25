package org.curtis.musicxml.note.notation.technical;

public class Hole extends Technical {
    private String holeType;
    private String holeClosedValue;
    private String holeClosedLocation;
    private String holeShape;
    // TODO: print style
    // TODO: placement

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
}
