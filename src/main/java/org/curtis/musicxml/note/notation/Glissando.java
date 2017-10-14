package org.curtis.musicxml.note.notation;

public class Glissando extends Notation {
    private String value;
    private String type;
    // TODO: number
    // TODO: line type
    // TODO: dashed formatting
    // TODO: print style

    public Glissando() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
