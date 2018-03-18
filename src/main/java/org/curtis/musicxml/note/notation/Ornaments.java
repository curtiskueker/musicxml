package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.note.notation.ornament.Ornament;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ornaments")
public class Ornaments extends Notation {
    @Transient
    private List<Ornament> ornaments = new ArrayList<>();
    @Transient
    private List<AccidentalMark> accidentalMarks = new ArrayList<>();

    public Ornaments() {

    }

    public List<Ornament> getOrnaments() {
        return ornaments;
    }

    public void setOrnaments(List<Ornament> ornaments) {
        this.ornaments = ornaments;
    }

    public List<AccidentalMark> getAccidentalMarks() {
        return accidentalMarks;
    }

    public void setAccidentalMarks(List<AccidentalMark> accidentalMarks) {
        this.accidentalMarks = accidentalMarks;
    }
}
