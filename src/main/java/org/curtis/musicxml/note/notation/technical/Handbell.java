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
    @Column(name = "handbell_type")
    private HandbellType handbellType;

    public Handbell() {

    }

    public HandbellType getHandbellType() {
        return handbellType;
    }

    public void setHandbellType(HandbellType handbellType) {
        this.handbellType = handbellType;
    }
}
