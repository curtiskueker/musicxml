package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Location;

public class Arpeggiate extends Notation {
    private Integer number;
    private Location direction;
    private Position position;
    private Location placement;
    private String color;

    public Arpeggiate() {

    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Location getDirection() {
        return direction;
    }

    public void setDirection(Location direction) {
        this.direction = direction;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Location getPlacement() {
        return placement;
    }

    public void setPlacement(Location placement) {
        this.placement = placement;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
