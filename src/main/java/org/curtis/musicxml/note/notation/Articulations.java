package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.note.notation.articulation.Articulation;

import java.util.List;

public class Articulations extends Notation {
    private List<Articulation> articulationList;

    public Articulations() {

    }

    public List<Articulation> getArticulationList() {
        return articulationList;
    }

    public void setArticulationList(List<Articulation> articulationList) {
        this.articulationList = articulationList;
    }
}
