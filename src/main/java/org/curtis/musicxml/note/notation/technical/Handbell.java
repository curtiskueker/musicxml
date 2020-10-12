package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.converter.HandbellTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("handbell")
public class Handbell extends Technical {
    @Convert(converter = HandbellTypeConverter.class)
    @Column
    private HandbellType value;

    public Handbell() {

    }

    public HandbellType getValue() {
        return value;
    }

    public void setValue(HandbellType value) {
        this.value = value;
    }
}
