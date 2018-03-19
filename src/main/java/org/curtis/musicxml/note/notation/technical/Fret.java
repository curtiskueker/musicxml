package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Font;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("fret")
public class Fret extends Technical {
    @Transient
    private Integer value;
    @Transient
    private Font font;
    @Transient
    private String color;

    public Fret() {

    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
