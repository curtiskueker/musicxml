package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.EmptyPlacement;

public abstract class HeelToe extends Technical {
    private EmptyPlacement value;
    private Boolean substitution;

    public EmptyPlacement getValue() {
        return value;
    }

    public void setValue(EmptyPlacement value) {
        this.value = value;
    }

    public Boolean getSubstitution() {
        return substitution;
    }

    public void setSubstitution(Boolean substitution) {
        this.substitution = substitution;
    }
}
