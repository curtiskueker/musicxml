package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.note.notation.ornament.Ornament;

import java.util.List;

public class Ornaments extends Notation {
    private List<Ornament> ornaments;

    public Ornaments() {

    }

    public List<Ornament> getOrnaments() {
        return ornaments;
    }

    public void setOrnaments(List<Ornament> ornaments) {
        this.ornaments = ornaments;
    }
}
