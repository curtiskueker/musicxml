package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.Placement;

public abstract class HeelToe extends Technical {
    private Placement placement;
    private Boolean substitution;

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    public Boolean getSubstitution() {
        return substitution;
    }

    public void setSubstitution(Boolean substitution) {
        this.substitution = substitution;
    }
}
