package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.converter.ConnectionConverter;
import org.curtis.musicxml.converter.LineEndConverter;
import org.curtis.musicxml.converter.LineTypeConverter;
import org.curtis.musicxml.note.LineType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("bracket")
public class Bracket extends DirectionType {
    @Convert(converter = ConnectionConverter.class)
    @Column
    private Connection type;
    @Column(name = "direction_type_number")
    private Integer number;
    @Convert(converter = LineEndConverter.class)
    @Column(name = "line_end")
    private LineEnd lineEnd;
    @Column(name = "end_length", precision = 12, scale = 4)
    private BigDecimal endLength;
    @Convert(converter = LineTypeConverter.class)
    @Column(name = "line_type")
    private LineType lineType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dashed_formatting_id")
    private DashedFormatting dashedFormatting;

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
}
