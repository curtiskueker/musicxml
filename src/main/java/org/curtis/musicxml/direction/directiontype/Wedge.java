package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.note.LineType;

import java.math.BigDecimal;

public class Wedge extends DirectionType {
    private WedgeType type;
    private Integer number;
    private BigDecimal spread;
    private Boolean niente;
    private LineType lineType;
    private DashedFormatting dashedFormatting;
    private Position position;
    private String color;

    public Wedge() {

    }

    public WedgeType getType() {
        return type;
    }

    public void setType(WedgeType type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }

    public Boolean getNiente() {
        return niente;
    }

    public void setNiente(Boolean niente) {
        this.niente = niente;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
