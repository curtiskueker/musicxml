package org.curtis.musicxml.note.notation.ornament;

import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class HorizontalTurn extends Ornament {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trill_sound_id")
    private TrillSound trillSound;
    @Column
    @Type(type="yes_no")
    private Boolean slash;

    public TrillSound getTrillSound() {
        return trillSound;
    }

    public void setTrillSound(TrillSound trillSound) {
        this.trillSound = trillSound;
    }

    public Boolean getSlash() {
        return slash;
    }

    public void setSlash(Boolean slash) {
        this.slash = slash;
    }
}
