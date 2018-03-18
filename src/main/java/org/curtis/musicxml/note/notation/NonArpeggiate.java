package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Location;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("non arpeggiate")
public class NonArpeggiate extends Notation {
    @Transient
    private Location type;
    @Transient
    private Integer number;
    @Transient
    private Position position;
    @Transient
    private Location placement;
    @Transient
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
