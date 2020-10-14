package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.converter.ConnectionConverter;
import org.curtis.musicxml.converter.LineTypeConverter;
import org.curtis.musicxml.converter.OrientationConverter;
import org.curtis.musicxml.display.Orientation;
import org.curtis.musicxml.note.LineType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("slur")
public class Slur extends Notation {
    @Convert(converter = ConnectionConverter.class)
    @Column
    private Connection type;
    @Column(name = "notation_number")
    private Integer number;
    @Convert(converter = LineTypeConverter.class)
    @Column(name = "line_type")
    private LineType lineType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dashed_formatting_id")
    private DashedFormatting dashedFormatting;
    @Convert(converter = OrientationConverter.class)
    @Column
    private Orientation orientation;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bezier_id")
    private Bezier bezier;
    @Transient
    // used by lilypond
    private SlurType slurType;

    public Slur() {

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

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Bezier getBezier() {
        return bezier;
    }

    public void setBezier(Bezier bezier) {
        this.bezier = bezier;
    }

    public SlurType getSlurType() {
        return slurType;
    }

    public void setSlurType(SlurType slurType) {
        this.slurType = slurType;
    }
}
