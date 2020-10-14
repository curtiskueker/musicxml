package org.curtis.musicxml.note.notation;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.NoteTypeValueConverter;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.note.NoteTypeValue;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tuplet_type")
public class TupletType extends DatabaseItem {
    @Convert(converter = NoteTypeValueConverter.class)
    @Column
    private NoteTypeValue value;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public TupletType() {

    }

    public NoteTypeValue getValue() {
        return value;
    }

    public void setValue(NoteTypeValue value) {
        this.value = value;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
