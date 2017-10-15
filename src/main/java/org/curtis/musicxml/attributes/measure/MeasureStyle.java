package org.curtis.musicxml.attributes.measure;

import org.curtis.musicxml.common.Font;

public abstract class MeasureStyle {
    // TODO: number
    private Font font;
    private String color;

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
