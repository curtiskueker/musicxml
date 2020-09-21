package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.converter.PedalTypeConverter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("pedal")
public class Pedal extends DirectionType {
    @Convert(converter = PedalTypeConverter.class)
    @Column(name = "direction_type")
    private PedalType pedalType;
    @Column(name = "direction_type_number")
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
