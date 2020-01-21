package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.converter.BreathMarkTypeConverter;
import org.curtis.musicxml.display.Display;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("breath mark")
public class BreathMark extends Articulation {
    @Column(name = "breath_mark_value")
    @Convert(converter = BreathMarkTypeConverter.class)
    private BreathMarkType breathMarkValue;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;

    public BreathMark() {

    }

    public BreathMarkType getBreathMarkValue() {
        return breathMarkValue;
    }

    public void setBreathMarkValue(BreathMarkType breathMarkValue) {
        this.breathMarkValue = breathMarkValue;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
