package org.curtis.musicxml.note.notation.technical;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("tap")
public class Tap extends Technical {
    @Column
    private String value;
    @Enumerated(EnumType.STRING)
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
