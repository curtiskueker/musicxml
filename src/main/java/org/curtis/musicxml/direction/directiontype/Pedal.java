package org.curtis.musicxml.direction.directiontype;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("pedal")
public class Pedal extends DirectionType {
    @Enumerated(EnumType.STRING)
    @Column(name = "pedal_type")
    private PedalType pedalType;
    @Column(name = "pedal_number")
    private Integer number;
    @Column
    @Type(type="yes_no")
    private Boolean line;
    @Column
    @Type(type="yes_no")
    private Boolean sign;
    @Column
    @Type(type="yes_no")
    private Boolean abbreviated;

    public Pedal() {

    }

    public PedalType getPedalType() {
        return pedalType;
    }

    public void setPedalType(PedalType pedalType) {
        this.pedalType = pedalType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getLine() {
        return line;
    }

    public void setLine(Boolean line) {
        this.line = line;
    }

    public Boolean getSign() {
        return sign;
    }

    public void setSign(Boolean sign) {
        this.sign = sign;
    }

    public Boolean getAbbreviated() {
        return abbreviated;
    }

    public void setAbbreviated(Boolean abbreviated) {
        this.abbreviated = abbreviated;
    }
}
