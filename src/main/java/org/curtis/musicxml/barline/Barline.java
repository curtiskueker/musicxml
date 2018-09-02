package org.curtis.musicxml.barline;

import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.PrintStyleAlign;
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
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("barline")
public class Barline extends MusicData {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bar_style_id")
    private BarStyleColor barStyle;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;
    /*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wavy_line_id")
    */
    @Transient
    private WavyLine wavyLine;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "segno_print_id")
    private PrintStyleAlign segnoPrint;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coda_print_id")
    private PrintStyleAlign codaPrint;
    /*
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "barline_id")
    */
    @Transient
    private List<Fermata> fermataList = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ending_id")
    private Ending ending;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "repeat_id")
    private Repeat repeat;
    @Enumerated(EnumType.STRING)
    @Column
    private Location location;
    @Column
    private String segno;
    @Column
    private String coda;
    @Column
    private BigDecimal divisions;

    public Barline() {

    }

    public BarStyleColor getBarStyle() {
        return barStyle;
    }

    public void setBarStyle(BarStyleColor barStyle) {
        this.barStyle = barStyle;
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

    public PrintStyleAlign getSegnoPrint() {
        return segnoPrint;
    }

    public void setSegnoPrint(PrintStyleAlign segnoPrint) {
        this.segnoPrint = segnoPrint;
    }

    public PrintStyleAlign getCodaPrint() {
        return codaPrint;
    }

    public void setCodaPrint(PrintStyleAlign codaPrint) {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
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
