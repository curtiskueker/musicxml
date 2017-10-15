package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Font;

public class Fret extends Technical {
    private Integer value;
    private Font font;
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
