package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.converter.HarmonicPitchConverter;
import org.curtis.musicxml.converter.HarmonicTypeConverter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("harmonic")
public class Harmonic extends Technical {
    @Convert(converter = HarmonicTypeConverter.class)
    @Column
    private HarmonicType type;
    @Convert(converter = HarmonicPitchConverter.class)
    @Column(name = "harmonic_pitch")
    private HarmonicPitch harmonicPitch;
    @Column(name = "print_object")
    @Type(type="yes_no")
    private Boolean printObject;

    public Harmonic() {

    }

    public HarmonicType getType() {
        return type;
    }

    public void setType(HarmonicType type) {
        this.type = type;
    }

    public HarmonicPitch getHarmonicPitch() {
        return harmonicPitch;
    }

    public void setHarmonicPitch(HarmonicPitch harmonicPitch) {
        this.harmonicPitch = harmonicPitch;
    }

    public Boolean getPrintObject() {
        return printObject;
    }

    public void setPrintObject(Boolean printObject) {
        this.printObject = printObject;
    }
}
