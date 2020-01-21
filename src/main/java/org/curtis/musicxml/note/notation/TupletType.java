package org.curtis.musicxml.note.notation;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.display.Display;
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
    @JoinColumn(name = "display_id")
    private Display display;

    public TupletType() {

    }

    public NoteTypeValue getNoteTypeValue() {
        return noteTypeValue;
    }

    public void setNoteTypeValue(NoteTypeValue noteTypeValue) {
        this.noteTypeValue = noteTypeValue;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
