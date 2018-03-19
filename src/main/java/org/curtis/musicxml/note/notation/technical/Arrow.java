package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyle;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("arrow")
public class Arrow extends Technical {
    @Enumerated(EnumType.STRING)
    @Column(name = "arrow_direction")
    private ArrowDirection arrowDirection;
    @Enumerated(EnumType.STRING)
    @Column(name = "arrow_style")
    private ArrowStyle arrowStyle;
    @Enumerated(EnumType.STRING)
    @Column(name = "circular_arrow")
    private CircularArrow circularArrow;
    @Transient
    private PrintStyle printStyle;
    @Transient
    private Location placement;

    public Arrow() {

    }

    public ArrowDirection getArrowDirection() {
        return arrowDirection;
    }

    public void setArrowDirection(ArrowDirection arrowDirection) {
        this.arrowDirection = arrowDirection;
    }

    public ArrowStyle getArrowStyle() {
        return arrowStyle;
    }

    public void setArrowStyle(ArrowStyle arrowStyle) {
        this.arrowStyle = arrowStyle;
    }

    public CircularArrow getCircularArrow() {
        return circularArrow;
    }

    public void setCircularArrow(CircularArrow circularArrow) {
        this.circularArrow = circularArrow;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public Location getPlacement() {
        return placement;
    }

    public void setPlacement(Location placement) {
        this.placement = placement;
    }
}
