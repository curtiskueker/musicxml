package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.Position;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("wavy line")
public class WavyLine extends Ornament {
    @Enumerated(EnumType.STRING)
    @Column(name = "connection_type")
    private Connection type;
    @Column(name = "wavy_line_number")
    private Integer number;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    private Position position;
    @Enumerated(EnumType.STRING)
    @Column
    private Location placement;
    @Column
    private String color;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trill_sound_id")
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
