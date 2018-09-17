package org.curtis.musicxml.note.notation;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ornament_accidental")
public class OrnamentAccidental extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accidental_mark_id")
    private AccidentalMark accidentalMark;

    public OrnamentAccidental() {

    }

    public AccidentalMark getAccidentalMark() {
        return accidentalMark;
    }

    public void setAccidentalMark(AccidentalMark accidentalMark) {
        this.accidentalMark = accidentalMark;
    }
}
