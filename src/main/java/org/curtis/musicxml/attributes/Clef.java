package org.curtis.musicxml.attributes;

public class Clef {
    private String sign;
    private Integer line;
    private Integer clefOctaveChange;
    // TODO: number
    // TODO: additional
    // TODO: size
    // TODO: after barline
    // TODO: print style
    // TODO: print object

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
}
