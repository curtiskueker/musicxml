package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public abstract class PlacedTrillSound extends Ornament {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trill_sound_id")
    private TrillSound trillSound;

    public TrillSound getTrillSound() {
        return trillSound;
    }

    public void setTrillSound(TrillSound trillSound) {
        this.trillSound = trillSound;
    }
}
