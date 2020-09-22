package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.converter.CaesuraValueConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("caesura")
public class Caesura extends Articulation {
    @Column
    @Convert(converter = CaesuraValueConverter.class)
    private CaesuraValue value;

    public Caesura() {

    }

    public CaesuraValue getValue() {
        return value;
    }

    public void setValue(CaesuraValue value) {
        this.value = value;
    }
}
