package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Connection;

public class Dashes extends DirectionType {
    private Connection type;
    private Integer number;
    private DashedFormatting dashedFormatting;
    private Position position;
    private String color;

    public Dashes() {

    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
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
