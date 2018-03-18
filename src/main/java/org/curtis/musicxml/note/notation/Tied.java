package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.LineType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("tied")
public class Tied extends Notation {
    @Transient
    private Connection type;
    @Transient
    private Integer number;
    @Transient
    private LineType lineType;
    @Transient
    private DashedFormatting dashedFormatting;
    @Transient
    private Position position;
    @Transient
    private Location placement;
    @Transient
    private Location orientation;
    @Transient
    private Bezier bezier;
    @Transient
    private String color;

    public Tied() {

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

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
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

    public Location getPlacement() {
        return placement;
    }

    public void setPlacement(Location placement) {
        this.placement = placement;
    }

    public Location getOrientation() {
        return orientation;
    }

    public void setOrientation(Location orientation) {
        this.orientation = orientation;
    }

    public Bezier getBezier() {
        return bezier;
    }

    public void setBezier(Bezier bezier) {
        this.bezier = bezier;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
