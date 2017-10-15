package org.curtis.musicxml.attributes;

import org.curtis.musicxml.common.PrintStyle;

public class Clef {
    private String sign;
    private Integer line;
    private Integer clefOctaveChange;
    private Integer number;
    private Boolean additional;
    private String size;
    private Boolean afterBarline;
    private PrintStyle printStyle;
    private Boolean printObject;

    public Clef() {

    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getClefOctaveChange() {
        return clefOctaveChange;
    }

    public void setClefOctaveChange(Integer clefOctaveChange) {
        this.clefOctaveChange = clefOctaveChange;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getAdditional() {
        return additional;
    }

    public void setAdditional(Boolean additional) {
        this.additional = additional;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getAfterBarline() {
        return afterBarline;
    }

    public void setAfterBarline(Boolean afterBarline) {
        this.afterBarline = afterBarline;
    }

    public PrintStyle getPrintStyle() {
        return printStyle;
    }

    public void setPrintStyle(PrintStyle printStyle) {
        this.printStyle = printStyle;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }
}
