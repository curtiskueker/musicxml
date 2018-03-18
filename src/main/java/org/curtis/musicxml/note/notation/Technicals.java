package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.note.notation.technical.Technical;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("technicals")
public class Technicals extends Notation {
    @Transient
    private List<Technical> technicals = new ArrayList<>();

    public Technicals() {

    }

    public List<Technical> getTechnicals() {
        return technicals;
    }

    public void setTechnicals(List<Technical> technicals) {
        this.technicals = technicals;
    }
}
