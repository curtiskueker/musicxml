package org.curtis.musicxml.note;

import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.note.notation.Notation;

import java.util.List;

public class Notations {
    private Editorial editorial;
    private List<Notation> notations;
    private Boolean printObject;

    public Notations() {

    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public List<Notation> getNotations() {
        return notations;
    }

    public void setNotations(List<Notation> notations) {
        this.notations = notations;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }
}
