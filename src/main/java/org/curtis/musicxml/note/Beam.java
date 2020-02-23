package org.curtis.musicxml.note;

import org.curtis.database.DatabaseElement;
import org.curtis.musicxml.display.Display;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "beam")
public class Beam extends DatabaseElement {
    @Enumerated(EnumType.STRING)
    @Column
    private BeamType type;
    @Column(name = "beam_number")
    private Integer number = 1;
    @Column
    @Type(type="yes_no")
    private Boolean repeater;
    @Enumerated(EnumType.STRING)
    @Column
    private BeamFan fan;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public Beam() {

    }

    public BeamType getType() {
        return type;
    }

    public void setType(BeamType type) {
        this.type = type;
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
