package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Location;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("arpeggiate")
public class Arpeggiate extends Notation {
    @Column
    private Integer number;
    @Enumerated(EnumType.STRING)
    @Column
    private Location direction;
    @Transient
    private Position position;
    @Transient
    private Location placement;
    @Transient
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
