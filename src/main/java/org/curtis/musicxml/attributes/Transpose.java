package org.curtis.musicxml.attributes;

import org.curtis.database.OrderedElement;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "transpose")
public class Transpose extends OrderedElement {
    @Column
    private Integer diatonic;
    @Column(precision = 12, scale = 4)
    private BigDecimal chromatic;
    @Column(name = "octave_change")
    private Integer octaveChange;
    @Column
    @Type(type="yes_no")
    private Boolean doubled = false;
    @Column(name = "staff_number")
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
