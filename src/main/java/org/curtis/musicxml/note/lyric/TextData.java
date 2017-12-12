package org.curtis.musicxml.note.lyric;

import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.TextDecoration;

import java.math.BigDecimal;

public class TextData {
    private String value;
    private Font font;
    private String color;
    private TextDecoration textDecoration;
    private BigDecimal textRotation;
    private String letterSpacing;
    private String lang;
    private Location textDirection;

    public TextData() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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

    public TextDecoration getTextDecoration() {
        return textDecoration;
    }

    public void setTextDecoration(TextDecoration textDecoration) {
        this.textDecoration = textDecoration;
    }

    public BigDecimal getTextRotation() {
        return textRotation;
    }

    public void setTextRotation(BigDecimal textRotation) {
        this.textRotation = textRotation;
    }

    public String getLetterSpacing() {
        return letterSpacing;
    }

    public void setLetterSpacing(String letterSpacing) {
        this.letterSpacing = letterSpacing;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Location getTextDirection() {
        return textDirection;
    }

    public void setTextDirection(Location textDirection) {
        this.textDirection = textDirection;
    }
}
