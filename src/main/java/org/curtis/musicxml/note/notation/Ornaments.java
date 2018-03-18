package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.note.notation.ornament.Ornament;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ornaments")
public class Ornaments extends Notation {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "ornaments_id", nullable = false)
    private List<Ornament> ornaments = new ArrayList<>();

    public Ornaments() {

    }

    public List<Ornament> getOrnaments() {
        return ornaments;
    }

    public void setOrnaments(List<Ornament> ornaments) {
        this.ornaments = ornaments;
    }
}
