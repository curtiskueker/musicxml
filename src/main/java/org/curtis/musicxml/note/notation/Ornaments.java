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
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ornaments")
public class Ornaments extends Notation {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "ornaments_id")
    private List<Ornament> ornaments = new ArrayList<>();
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@Fetch(FetchMode.SUBSELECT)
    //@JoinColumn(name = "ornaments_id")
    // TODO: ornament accidental marks
    @Transient
    private List<AccidentalMark> accidentalMarks = new ArrayList<>();
    @Transient
    // used by lilypond
    private boolean printObject = true;

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

    public boolean isPrintObject() {
        return printObject;
    }

    public void setPrintObject(boolean printObject) {
        this.printObject = printObject;
    }
}
