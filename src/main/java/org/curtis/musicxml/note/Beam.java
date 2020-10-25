package org.curtis.musicxml.note;

import org.curtis.database.OrderedElement;
import org.curtis.musicxml.converter.BeamFanConverter;
import org.curtis.musicxml.converter.BeamTypeConverter;
import org.curtis.musicxml.display.Display;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "beam")
public class Beam extends OrderedElement {
    @Convert(converter = BeamTypeConverter.class)
    @Column
    private BeamType value;
    @Column(name = "beam_number")
    private Integer number = 1;
    @Column
    @Type(type="yes_no")
    private Boolean repeater;
    @Convert(converter = BeamFanConverter.class)
    @Column
    private BeamFan fan;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public Beam() {

    }

    public BeamType getValue() {
        return value;
    }

    public void setValue(BeamType value) {
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getRepeater() {
        return repeater;
    }

    public void setRepeater(Boolean repeater) {
        this.repeater = repeater;
    }

    public BeamFan getFan() {
        return fan;
    }

    public void setFan(BeamFan fan) {
        this.fan = fan;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
