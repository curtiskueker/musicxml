package org.curtis.musicxml.barline;

import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.score.MusicData;

import java.math.BigDecimal;

public class Barline extends MusicData {
    private BarStyleColor barStyle;
    // TODO: editorial
    // TODO: wavy line
    private PrintStyleAlign segnoPrint;
    private PrintStyleAlign codaPrint;
    // TODO: fermata
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
