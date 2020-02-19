package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.common.Connection;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class HammerOnPullOff extends Technical {
    @Column
    private String value;
    @Enumerated(EnumType.STRING)
    @Column(name = "notation_type")
    private Connection type;
    @Column(name = "notation_number")
    private Integer number = 1;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
