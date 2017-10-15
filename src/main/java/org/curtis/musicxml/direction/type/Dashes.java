package org.curtis.musicxml.direction.type;

import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.Position;

public class Dashes extends DirectionType {
    private String type;
    private Integer number;
    private DashedFormatting dashedFormatting;
    private Position position;
    private String color;

    public Dashes() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public DashedFormatting getDashedFormatting() {
        return dashedFormatting;
    }

    public void setDashedFormatting(DashedFormatting dashedFormatting) {
        this.dashedFormatting = dashedFormatting;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
