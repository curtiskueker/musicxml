package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.SymbolSize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "note_type")
public class NoteType extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column
    private NoteTypeValue value;
    @Enumerated(EnumType.STRING)
    @Column
    private SymbolSize size;

    public NoteType() {

    }

    public NoteTypeValue getValue() {
        return value;
    }

    public void setValue(NoteTypeValue value) {
        this.value = value;
    }

    public SymbolSize getSize() {
        return size;
    }

    public void setSize(SymbolSize size) {
        this.size = size;
    }
}
