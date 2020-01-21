package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.DashedFormatting;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.note.LineType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("tied")
public class Tied extends Notation {
    @Enumerated(EnumType.STRING)
    @Column(name = "tied_type")
    private TiedType tiedType;
    @Column(name = "notation_number")
    private Integer number;
    @Enumerated(EnumType.STRING)
    @Column(name = "line_type")
    private LineType lineType;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dashed_formatting_id")
    private DashedFormatting dashedFormatting;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Enumerated(EnumType.STRING)
    @Column
    private Location orientation;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bezier_id")
    private Bezier bezier;
    // used by lilypond
    @Transient
    private boolean unterminated = false;
    // used by lilypond
    @Transient
    private boolean isRepeatTie = false;

    public Tied() {

    }

    public TiedType getTiedType() {
        return tiedType;
    }

    public void setTiedType(TiedType tiedType) {
        this.tiedType = tiedType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    public DashedFormatting getDashedFormatting() {
        return dashedFormatting;
    }

    public void setDashedFormatting(DashedFormatting dashedFormatting) {
        this.dashedFormatting = dashedFormatting;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Location getOrientation() {
        return orientation;
    }

    public void setOrientation(Location orientation) {
        this.orientation = orientation;
    }

    public Bezier getBezier() {
        return bezier;
    }

    public void setBezier(Bezier bezier) {
        this.bezier = bezier;
    }

    public boolean isUnterminated() {
        return unterminated;
    }

    public void setUnterminated(boolean unterminated) {
        this.unterminated = unterminated;
    }

    public boolean isRepeatTie() {
        return isRepeatTie;
    }

    public void setRepeatTie(boolean repeatTie) {
        isRepeatTie = repeatTie;
    }
}
