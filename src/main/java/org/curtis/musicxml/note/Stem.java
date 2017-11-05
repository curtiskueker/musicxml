package org.curtis.musicxml.note;

public class Stem {
    private StemType type;
    private YPosition yPosition;
    private String color;

    public Stem() {

    }

    public StemType getType() {
        return type;
    }

    public void setType(StemType type) {
        this.type = type;
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
