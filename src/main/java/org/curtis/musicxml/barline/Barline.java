package org.curtis.musicxml.barline;

import org.curtis.musicxml.display.Editorial;
import org.curtis.musicxml.direction.directiontype.Coda;
import org.curtis.musicxml.direction.directiontype.Segno;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.ornament.WavyLine;
import org.curtis.musicxml.score.MusicData;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("barline")
public class Barline extends MusicData {
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
    private List<Fermata> fermataList = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ending_id")
    private Ending ending;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "repeat_id")
    private Repeat repeat;
    @Enumerated(EnumType.STRING)
    @Column
    private BarlineLocation location;
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

    public Ending getEnding() {
        return ending;
    }

    public void setEnding(Ending ending) {
        this.ending = ending;
    }

    public Repeat getRepeat() {
        return repeat;
    }

    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }

    public BarlineLocation getLocation() {
        return location;
    }

    public void setLocation(BarlineLocation location) {
        this.location = location;
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
