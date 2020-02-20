package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.converter.BreathMarkTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("breath mark")
public class BreathMark extends Articulation {
    @Column(name = "breath_mark_value")
    @Convert(converter = BreathMarkTypeConverter.class)
    private BreathMarkType breathMarkValue;

    public BreathMark() {

    }

    public BreathMarkType getBreathMarkValue() {
        return breathMarkValue;
    }

    public void setBreathMarkValue(BreathMarkType breathMarkValue) {
        this.breathMarkValue = breathMarkValue;
    }
}
