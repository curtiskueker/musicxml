package org.curtis.musicxml.attributes.key;

import org.curtis.database.DatabaseItem;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "key_octave")
public class KeyOctave extends DatabaseItem {
    @Column
    private Integer octave;
    @Column(name = "key_octave_number")
    private Integer number;
    @Column
    @Type(type="yes_no")
    private Boolean cancel;

    public KeyOctave() {

    }

    public Integer getOctave() {
        return octave;
    }

    public void setOctave(Integer octave) {
        this.octave = octave;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }
}
