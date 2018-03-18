package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "beam")
public class Beam extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column
    private BeamType type;
    @Column
    private Integer number = 1;
    @Transient
    private Boolean repeater;
    @Enumerated(EnumType.STRING)
    @Column
    private BeamFan fan;
    @Transient
    private String color;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
