package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("tremolo")
public class Tremolo extends Ornament {
    @Column(name = "tremolo_marks")
    private Integer tremoloMarks;
    @Enumerated(EnumType.STRING)
    @Column(name = "tremolo_type")
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
