package org.curtis.musicxml.barline;

import org.curtis.musicxml.display.Editorial;
import org.curtis.musicxml.direction.directiontype.Coda;
import org.curtis.musicxml.direction.directiontype.Segno;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.ornament.WavyLine;
import org.curtis.musicxml.score.MusicDataElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("barline")
public class Barline extends MusicDataElement {
    @Enumerated(EnumType.STRING)
    @Column(name = "bar_style")
    private BarStyle barStyle;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wavy_line_id")
    private WavyLine wavyLine;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "segno_id")
    private Segno segnoPrint;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coda_id")
    private Coda codaPrint;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "barline_id")
    @OrderBy("ordering")
    private List<Fermata> fermataList = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "barline_ending_id")
    private BarlineEnding ending;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "barline_repeat_id")
    private BarlineRepeat repeat;
    @Enumerated(EnumType.STRING)
    @Column(name = "barline_location")
    private BarlineLocation barlineLocation;
    @Column
    private String segno;
    @Column
    private String coda;
    @Column(precision = 12, scale = 4)
    private BigDecimal divisions;

    public Barline() {

    }

    public BarStyle getBarStyle() {
        return barStyle;
    }

    public void setBarStyle(BarStyle barStyle) {
        this.barStyle = barStyle;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public WavyLine getWavyLine() {
        return wavyLine;
    }

    public void setWavyLine(WavyLine wavyLine) {
        this.wavyLine = wavyLine;
    }

    public Segno getSegnoPrint() {
        return segnoPrint;
    }

    public void setSegnoPrint(Segno segnoPrint) {
        this.segnoPrint = segnoPrint;
    }

    public Coda getCodaPrint() {
        return codaPrint;
    }

    public void setCodaPrint(Coda codaPrint) {
        this.codaPrint = codaPrint;
    }

    public List<Fermata> getFermataList() {
        return fermataList;
    }

    public void setFermataList(List<Fermata> fermataList) {
        this.fermataList = fermataList;
    }

    public BarlineEnding getEnding() {
        return ending;
    }

    public void setEnding(BarlineEnding ending) {
        this.ending = ending;
    }

    public BarlineRepeat getRepeat() {
        return repeat;
    }

    public void setRepeat(BarlineRepeat repeat) {
        this.repeat = repeat;
    }

    public BarlineLocation getBarlineLocation() {
        return barlineLocation;
    }

    public void setBarlineLocation(BarlineLocation barlineLocation) {
        this.barlineLocation = barlineLocation;
    }

    public String getSegno() {
        return segno;
    }

    public void setSegno(String segno) {
        this.segno = segno;
    }

    public String getCoda() {
        return coda;
    }

    public void setCoda(String coda) {
        this.coda = coda;
    }

    public BigDecimal getDivisions() {
        return divisions;
    }

    public void setDivisions(BigDecimal divisions) {
        this.divisions = divisions;
    }
}
