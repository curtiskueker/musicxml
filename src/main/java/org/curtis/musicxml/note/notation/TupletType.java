package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.note.NoteTypeValue;

public class TupletType {
    private NoteTypeValue noteTypeValue;
    private Font font;
    private String color;

    public TupletType() {

    }

    public NoteTypeValue getNoteTypeValue() {
        return noteTypeValue;
    }

    public void setNoteTypeValue(NoteTypeValue noteTypeValue) {
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
