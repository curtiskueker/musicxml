package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.Position;

public class Arpeggiate extends Notation {
    private Integer number;
    private String direction;
    private Position position;
    private String placement;
    private String color;

    public Arpeggiate() {

    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
