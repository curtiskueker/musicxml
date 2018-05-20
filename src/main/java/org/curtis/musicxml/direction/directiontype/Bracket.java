package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.LineType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("bracket")
public class Bracket extends DirectionType {
    @Enumerated(EnumType.STRING)
    @Column
    private Connection type;
    @Column
    private Integer number;
    @Enumerated(EnumType.STRING)
    @Column(name = "line_end")
    private LineEnd lineEnd;
    @Column(name = "end_length")
    private BigDecimal endLength;
    @Transient
    private LineType lineType;
    @Transient
    private DashedFormatting dashedFormatting;
    @Transient
    private Position position;
    @Transient
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
