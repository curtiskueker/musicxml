package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Location;

public class NonArpeggiate extends Notation {
    private Location type;
    private Integer number;
    private Position position;
    private Location placement;
    private String color;

    public NonArpeggiate() {

    }

    public Location getType() {
        return type;
    }

    public void setType(Location type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
