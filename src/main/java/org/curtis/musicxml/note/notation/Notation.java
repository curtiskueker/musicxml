package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.Editorial;

public abstract class Notation {
    private Editorial editorial;
    private Boolean printObject;

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }
}
