package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.Placement;

public abstract class HeelToe extends Technical {
    private Placement value;
    private Boolean substitution;

    public Placement getValue() {
        return value;
    }

    public void setValue(Placement value) {
        this.value = value;
    }

    public Boolean getSubstitution() {
        return substitution;
    }

    public void setSubstitution(Boolean substitution) {
        this.substitution = substitution;
    }
}
