package org.curtis.musicxml.note.notation.ornament;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.note.notation.AccidentalMark;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "ornament")
@DiscriminatorColumn(name = "ornament_type")
public abstract class Ornament extends DatabaseItem {
    @Transient
    private List<AccidentalMark> accidentalMarks = new ArrayList<>();

    public List<AccidentalMark> getAccidentalMarks() {
        return accidentalMarks;
    }

    public void setAccidentalMarks(List<AccidentalMark> accidentalMarks) {
        this.accidentalMarks = accidentalMarks;
    }
}
