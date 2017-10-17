package org.curtis.musicxml.direction.type;

import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.LineType;

import java.math.BigDecimal;

public class Bracket extends DirectionType {
    private Connection type;
    private Integer number;
    private LineEnd lineEnd;
    private BigDecimal endLength;
    private LineType lineType;
    private DashedFormatting dashedFormatting;
    private Position position;
    private String color;

    public Bracket() {

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

    public LineEnd getLineEnd() {
        return lineEnd;
    }

    public void setLineEnd(LineEnd lineEnd) {
        this.lineEnd = lineEnd;
    }

    public BigDecimal getEndLength() {
        return endLength;
    }

    public void setEndLength(BigDecimal endLength) {
        this.endLength = endLength;
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
