package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.note.notation.technical.Technical;

import java.util.ArrayList;
import java.util.List;

public class Technicals extends Notation {
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
