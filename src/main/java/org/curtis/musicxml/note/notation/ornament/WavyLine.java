package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Connection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("wavy line")
public class WavyLine extends Ornament {
    @Transient
    private Connection type;
    @Transient
    private Integer number;
    @Transient
    private Position position;
    @Transient
    private Location placement;
    @Transient
    private String color;
    @Transient
    private TrillSound trillSound;

    public WavyLine() {

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

    public TrillSound getTrillSound() {
        return trillSound;
    }

    public void setTrillSound(TrillSound trillSound) {
        this.trillSound = trillSound;
    }
}
