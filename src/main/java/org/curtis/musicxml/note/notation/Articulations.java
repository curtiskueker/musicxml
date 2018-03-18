package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.note.notation.articulation.Articulation;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("articulations")
public class Articulations extends Notation {
    @Transient
    private List<Articulation> articulationList = new ArrayList<>();

    public Articulations() {

    }

    public List<Articulation> getArticulationList() {
        return articulationList;
    }

    public void setArticulationList(List<Articulation> articulationList) {
        this.articulationList = articulationList;
    }
}
