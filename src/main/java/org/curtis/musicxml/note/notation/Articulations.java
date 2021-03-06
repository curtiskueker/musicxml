package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.note.notation.articulation.Articulation;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("articulations")
public class Articulations extends Notation {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "articulations_id", nullable = false)
    @OrderBy("ordering")
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
