package org.curtis.musicxml.note.notation;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.note.NoteTypeValue;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tuplet_type")
public class TupletType extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "note_type_value")
    private NoteTypeValue noteTypeValue;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "font_id")
    private Font font;
    @Column
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
