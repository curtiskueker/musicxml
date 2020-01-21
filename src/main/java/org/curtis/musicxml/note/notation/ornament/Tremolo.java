package org.curtis.musicxml.note.notation.ornament;

import org.curtis.musicxml.display.Display;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("tremolo")
public class Tremolo extends Ornament {
    @Column(name = "tremolo_marks")
    private Integer tremoloMarks;
    @Enumerated(EnumType.STRING)
    @Column(name = "tremolo_type")
    private TremoloType tremoloType = TremoloType.SINGLE;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
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

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
