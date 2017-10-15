package org.curtis.musicxml.barline;

import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.note.notation.ornament.WavyLine;
import org.curtis.musicxml.score.MusicData;

import java.math.BigDecimal;
import java.util.List;

public class Barline extends MusicData {
    private BarStyleColor barStyle;
    private Editorial editorial;
    private WavyLine wavyLine;
    private PrintStyleAlign segnoPrint;
    private PrintStyleAlign codaPrint;
    private List<Fermata> fermataList;
    private Ending ending;
    private Repeat repeat;
    private String location;
    private String segno;
    private String coda;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
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
