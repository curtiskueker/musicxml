package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.converter.BreathMarkTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("breath mark")
public class BreathMark extends Articulation {
    @Column
    @Convert(converter = BreathMarkTypeConverter.class)
    private BreathMarkType value;

    public BreathMark() {

    }

    public BreathMarkType getValue() {
        return value;
    }

    public void setValue(BreathMarkType value) {
        this.value = value;
    }
}
