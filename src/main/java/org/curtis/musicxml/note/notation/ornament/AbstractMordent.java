package org.curtis.musicxml.note.notation.ornament;

public class AbstractMordent extends Ornament {
    private EmptyTrillSound emptyTrillSound;
    // TODO: long
    // TODO: approach
    // TODO: departure

    public AbstractMordent() {

    }

    public EmptyTrillSound getEmptyTrillSound() {
        return emptyTrillSound;
    }

    public void setEmptyTrillSound(EmptyTrillSound emptyTrillSound) {
        this.emptyTrillSound = emptyTrillSound;
    }
}
