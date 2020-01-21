package org.curtis.musicxml.note;

import org.curtis.musicxml.common.TextDisplay;
import org.curtis.musicxml.common.TextFormatting;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("accidental_text")
public class AccidentalText extends TextDisplay {
    @Enumerated(EnumType.STRING)
    @Column(name = "accidental_type")
    private AccidentalType accidentalType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "text_formatting_id")
    private TextFormatting textFormatting;
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

    public TextFormatting getTextFormatting() {
        return textFormatting;
    }

    public void setTextFormatting(TextFormatting textFormatting) {
        this.textFormatting = textFormatting;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
