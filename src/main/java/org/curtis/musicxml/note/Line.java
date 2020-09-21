package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.converter.LineLengthConverter;
import org.curtis.musicxml.converter.LineShapeConverter;
import org.curtis.musicxml.converter.LineTypeConverter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "line")
public class Line extends DatabaseItem {
    @Convert(converter = LineShapeConverter.class)
    @Column(name = "line_shape")
    private LineShape lineShape;
    @Convert(converter = LineTypeConverter.class)
    @Column(name = "line_type")
    private LineType lineType;
    @Convert(converter = LineLengthConverter.class)
    @Column(name = "line_length")
    private LineLength lineLength;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dashed_formatting_id")
    private DashedFormatting dashedFormatting;

    public Line() {

    }

    public LineShape getLineShape() {
        return lineShape;
    }

    public void setLineShape(LineShape lineShape) {
        this.lineShape = lineShape;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    public LineLength getLineLength() {
        return lineLength;
    }

    public void setLineLength(LineLength lineLength) {
        this.lineLength = lineLength;
    }

    public DashedFormatting getDashedFormatting() {
        return dashedFormatting;
    }

    public void setDashedFormatting(DashedFormatting dashedFormatting) {
        this.dashedFormatting = dashedFormatting;
    }
}
