package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.converter.TremoloTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("tremolo")
public class Tremolo extends Ornament {
    @Column(name = "tremolo_marks")
    private Integer tremoloMarks;
    @Convert(converter = TremoloTypeConverter.class)
    @Column
    private TremoloType type = TremoloType.SINGLE;
    @Column
    private String smufl;

    public Tremolo() {

    }

    public Integer getTremoloMarks() {
        return tremoloMarks;
    }

    public void setTremoloMarks(Integer tremoloMarks) {
        this.tremoloMarks = tremoloMarks;
    }

    public TremoloType getType() {
        return type;
    }

    public void setType(TremoloType type) {
        this.type = type;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
