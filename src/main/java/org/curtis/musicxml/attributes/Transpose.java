package org.curtis.musicxml.attributes;

import java.math.BigDecimal;

public class Transpose {
    private Integer diatonic;
    private BigDecimal chromatic;
    private Integer octaveChange;
    private Boolean doubled;
    private Integer number;

    public Transpose() {

    }

    public Integer getDiatonic() {
        return diatonic;
    }

    public void setDiatonic(Integer diatonic) {
        this.diatonic = diatonic;
    }

    public BigDecimal getChromatic() {
        return chromatic;
    }

    public void setChromatic(BigDecimal chromatic) {
        this.chromatic = chromatic;
    }

    public Integer getOctaveChange() {
        return octaveChange;
    }

    public void setOctaveChange(Integer octaveChange) {
        this.octaveChange = octaveChange;
    }

    public Boolean getDoubled() {
        return doubled;
    }

    public void setDoubled(Boolean doubled) {
        this.doubled = doubled;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
