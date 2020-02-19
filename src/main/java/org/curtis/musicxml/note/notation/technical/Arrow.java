package org.curtis.musicxml.note.notation.technical;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("arrow")
public class Arrow extends Technical {
    @Enumerated(EnumType.STRING)
    @Column(name = "arrow_direction")
    private ArrowDirection arrowDirection;
    @Enumerated(EnumType.STRING)
    @Column(name = "arrow_style")
    private ArrowStyle arrowStyle;
    @Column
    @Type(type="yes_no")
    private Boolean arrowhead = false;
    @Enumerated(EnumType.STRING)
    @Column(name = "circular_arrow")
    private CircularArrow circularArrow;
    @Column
    private String smufl;

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

    public Boolean getArrowhead() {
        return arrowhead;
    }

    public void setArrowhead(Boolean arrowhead) {
        this.arrowhead = arrowhead;
    }

    public CircularArrow getCircularArrow() {
        return circularArrow;
    }

    public void setCircularArrow(CircularArrow circularArrow) {
        this.circularArrow = circularArrow;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
