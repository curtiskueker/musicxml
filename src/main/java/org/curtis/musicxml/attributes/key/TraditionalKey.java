package org.curtis.musicxml.attributes.key;

import org.curtis.musicxml.converter.CancelLocationConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("traditional key")
public class TraditionalKey extends Key {
    @Column(name = "cancel_fifths")
    private Integer cancelFifths;
    @Column(name = "cancel_location")
    @Convert(converter = CancelLocationConverter.class)
    private CancelLocation cancelLocation;
    @Column
    private Integer fifths;
    @Column(name = "key_mode")
    private String mode;

    public TraditionalKey() {

    }

    public Integer getCancelFifths() {
        return cancelFifths;
    }

    public void setCancelFifths(Integer cancelFifths) {
        this.cancelFifths = cancelFifths;
    }

    public CancelLocation getCancelLocation() {
        return cancelLocation;
    }

    public void setCancelLocation(CancelLocation cancelLocation) {
        this.cancelLocation = cancelLocation;
    }

    public boolean hasCancel() {
        return cancelFifths != null;
    }

    public Integer getFifths() {
        return fifths;
    }

    public void setFifths(Integer fifths) {
        this.fifths = fifths;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
