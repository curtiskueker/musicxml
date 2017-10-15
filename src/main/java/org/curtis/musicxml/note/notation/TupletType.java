package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.Font;

public class TupletType {
    private String noteTypeValue;
    private Font font;
    private String color;

    public TupletType() {

    }

    public String getNoteTypeValue() {
        return noteTypeValue;
    }

    public void setNoteTypeValue(String noteTypeValue) {
        this.noteTypeValue = noteTypeValue;
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
