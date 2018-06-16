package org.curtis.musicxml.attributes;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table(name = "transpose")
public class Transpose extends DatabaseItem {
    @Column
    private Integer diatonic;
    @Transient
    private BigDecimal chromatic;
    @Column(name = "octave_change")
    private Integer octaveChange;
    @Column
    private Boolean doubled = false;
    @Column
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
