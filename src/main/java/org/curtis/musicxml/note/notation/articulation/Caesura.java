package org.curtis.musicxml.note.notation.articulation;

import org.curtis.musicxml.converter.CaesuraValueConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("caesura")
public class Caesura extends Articulation {
    @Column(name = "caesura_value")
    @Convert(converter = CaesuraValueConverter.class)
    private CaesuraValue caesuraValue;

    public Caesura() {

    }

    public CaesuraValue getCaesuraValue() {
        return caesuraValue;
    }

    public void setCaesuraValue(CaesuraValue caesuraValue) {
        this.caesuraValue = caesuraValue;
    }
}
