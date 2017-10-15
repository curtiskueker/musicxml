package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.EmptyPlacement;

public class StrongAccent extends Articulation {
    private EmptyPlacement value;
    private String type = "up";

    public StrongAccent() {

    }

    public EmptyPlacement getValue() {
        return value;
    }

    public void setValue(EmptyPlacement value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
