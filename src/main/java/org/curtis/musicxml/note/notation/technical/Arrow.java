package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.converter.ArrowDirectionConverter;
import org.curtis.musicxml.converter.ArrowStyleConverter;
import org.curtis.musicxml.converter.CircularArrowConverter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("arrow")
public class Arrow extends Technical {
    @Convert(converter = ArrowDirectionConverter.class)
    @Column(name = "arrow_direction")
    private ArrowDirection arrowDirection;
    @Convert(converter = ArrowStyleConverter.class)
    @Column(name = "arrow_style")
    private ArrowStyle arrowStyle;
    @Column
    @Type(type="yes_no")
    private Boolean arrowhead = false;
    @Convert(converter = CircularArrowConverter.class)
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
