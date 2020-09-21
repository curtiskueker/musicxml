package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.converter.TapHandConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("tap")
public class Tap extends Technical {
    @Column
    private String value;
    @Convert(converter = TapHandConverter.class)
    @Column(name = "tap_hand")
    private TapHand tapHand;

    public Tap() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TapHand getTapHand() {
        return tapHand;
    }

    public void setTapHand(TapHand tapHand) {
        this.tapHand = tapHand;
    }
}
