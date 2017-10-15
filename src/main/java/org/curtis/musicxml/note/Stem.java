package org.curtis.musicxml.note;

public class Stem {
    private String value;
    private YPosition yPosition;
    private String color;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
