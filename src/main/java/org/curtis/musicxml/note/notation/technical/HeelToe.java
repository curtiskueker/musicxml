package org.curtis.musicxml.note.notation.technical;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class HeelToe extends Technical {
    @Column
    private Boolean substitution;

    public Boolean getSubstitution() {
        return substitution;
    }

    public void setSubstitution(Boolean substitution) {
        this.substitution = substitution;
    }
}
