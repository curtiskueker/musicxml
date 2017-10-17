package org.curtis.musicxml.note;

import org.curtis.musicxml.common.SymbolSize;

public class NoteType {
    private NoteTypeValue value;
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
