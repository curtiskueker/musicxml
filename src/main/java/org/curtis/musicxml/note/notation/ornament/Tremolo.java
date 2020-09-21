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
    @Column(name = "type")
    private TremoloType tremoloType = TremoloType.SINGLE;
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

    public TremoloType getTremoloType() {
        return tremoloType;
    }

    public void setTremoloType(TremoloType tremoloType) {
        this.tremoloType = tremoloType;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
