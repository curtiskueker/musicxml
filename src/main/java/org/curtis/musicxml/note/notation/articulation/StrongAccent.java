package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.note.Placement;

public class StrongAccent extends Articulation {
    private Placement value;
    private String type = "up";

    public StrongAccent() {

    }

    public Placement getValue() {
        return value;
    }

    public void setValue(Placement value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
