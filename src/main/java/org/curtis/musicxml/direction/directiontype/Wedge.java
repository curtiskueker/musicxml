package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.converter.LineTypeConverter;
import org.curtis.musicxml.converter.WedgeTypeConverter;
import org.curtis.musicxml.note.LineType;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("wedge")
public class Wedge extends DirectionType {
    @Convert(converter = WedgeTypeConverter.class)
    @Column
    private WedgeType type;
    @Column(name = "direction_type_number")
    private Integer number;
    @Column(precision = 12, scale = 4)
    private BigDecimal spread;
    @Column
    @Type(type="yes_no")
    private Boolean niente;
    @Convert(converter = LineTypeConverter.class)
    @Column(name = "line_type")
    private LineType lineType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dashed_formatting_id")
    private DashedFormatting dashedFormatting;

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
}
