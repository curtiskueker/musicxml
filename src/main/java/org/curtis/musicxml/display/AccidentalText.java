package org.curtis.musicxml.display;

import org.curtis.musicxml.converter.AccidentalTypeConverter;
import org.curtis.musicxml.note.AccidentalType;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("accidental_text")
public class AccidentalText extends TextDisplay {
    @Convert(converter = AccidentalTypeConverter.class)
    @Column(name = "accidental_type")
    private AccidentalType accidentalType;
    @Column
    private String smufl;

    public AccidentalText() {

    }

    public AccidentalType getAccidentalType() {
        return accidentalType;
    }

    public void setAccidentalType(AccidentalType accidentalType) {
        this.accidentalType = accidentalType;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
