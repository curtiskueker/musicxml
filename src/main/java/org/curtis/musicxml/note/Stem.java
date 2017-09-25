package org.curtis.musicxml.note;

public class Stem {
    private String value;
    private YPosition yPosition;
    // TODO: color

    public Stem() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public YPosition getyPosition() {
        return yPosition;
    }

    public void setyPosition(YPosition yPosition) {
        this.yPosition = yPosition;
    }
}
