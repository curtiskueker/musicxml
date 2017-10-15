package org.curtis.musicxml.direction.harmony;

import org.curtis.musicxml.common.PrintStyle;

import java.math.BigDecimal;

public class DegreeAlter {
    private BigDecimal semitones;
    private PrintStyle printStyle;
    private Boolean plusMinus;

    public DegreeAlter() {

    }

    public BigDecimal getSemitones() {
        return semitones;
    }

    public void setSemitones(BigDecimal semitones) {
        this.semitones = semitones;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public Boolean getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(Boolean plusMinus) {
        this.plusMinus = plusMinus;
    }
}
