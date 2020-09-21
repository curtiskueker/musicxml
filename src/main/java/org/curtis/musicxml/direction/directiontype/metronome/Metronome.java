package org.curtis.musicxml.direction.directiontype.metronome;

import org.curtis.musicxml.converter.HalignConverter;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.display.Halign;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Metronome extends DirectionType {
    @Convert(converter = HalignConverter.class)
    @Column
    private Halign justify;
    @Column
    @Type(type="yes_no")
    private Boolean parentheses;

    public Halign getJustify() {
        return justify;
    }

    public void setJustify(Halign justify) {
        this.justify = justify;
    }

    public Boolean getParentheses() {
        return parentheses;
    }

    public void setParentheses(Boolean parentheses) {
        this.parentheses = parentheses;
    }
}
