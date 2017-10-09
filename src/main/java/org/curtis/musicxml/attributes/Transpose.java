package org.curtis.musicxml.attributes;

public class Transpose {
    private Integer diatonic;
    // TODO: chromatic
    private Integer octaveChange;
    // TODO: double
    // TODO: number

    public Transpose() {

    }

    public Integer getDiatonic() {
        return diatonic;
    }

    public void setDiatonic(Integer diatonic) {
        this.diatonic = diatonic;
    }

    public Integer getOctaveChange() {
        return octaveChange;
    }

    public void setOctaveChange(Integer octaveChange) {
        this.octaveChange = octaveChange;
    }
}
