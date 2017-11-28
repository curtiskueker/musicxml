package org.curtis.musicxml.note;

import org.curtis.musicxml.common.Font;

public class Notehead {
    private NoteheadType type;
    private Boolean filled;
    private Boolean parentheses;
    private Font font;
    private String color;

    public Notehead() {

    }

    public NoteheadType getType() {
        return type;
    }

    public void setType(NoteheadType type) {
        this.type = type;
    }

    public Boolean getFilled() {
        return filled;
    }

    public void setFilled(Boolean filled) {
        this.filled = filled;
    }

    public Boolean getParentheses() {
        return parentheses;
    }

    public void setParentheses(Boolean parentheses) {
        this.parentheses = parentheses;
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
