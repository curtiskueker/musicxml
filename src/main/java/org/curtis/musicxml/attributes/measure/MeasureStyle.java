package org.curtis.musicxml.attributes.measure;

import org.curtis.musicxml.common.Font;

public abstract class MeasureStyle {
    private Integer number;
    private Font font;
    private String color;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
