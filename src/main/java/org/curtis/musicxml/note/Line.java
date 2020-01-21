package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.display.Display;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "line")
public class Line extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "line_shape")
    private LineShape lineShape;
    @Enumerated(EnumType.STRING)
    @Column(name = "line_type")
    private LineType lineType;
    @Enumerated(EnumType.STRING)
    @Column(name = "line_length")
    private LineLength lineLength;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dashed_formatting_id")
    private DashedFormatting dashedFormatting;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

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

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
