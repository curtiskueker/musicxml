package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.converter.FermataShapeConverter;
import org.curtis.musicxml.converter.FermataTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("fermata")
public class Fermata extends Notation {
    @Column(name = "fermata_shape")
    @Convert(converter = FermataShapeConverter.class)
    private FermataShape fermataShape;
    @Convert(converter = FermataTypeConverter.class)
    @Column(name = "type_value")
    private FermataType type;
    @Transient
    private boolean isMarkup = false;

    public Fermata() {

    }

    public FermataShape getFermataShape() {
        return fermataShape;
    }

    public void setFermataShape(FermataShape fermataShape) {
        this.fermataShape = fermataShape;
    }

    public FermataType getType() {
        return type;
    }

    public void setType(FermataType type) {
        this.type = type;
    }

    public boolean isMarkup() {
        return isMarkup;
    }

    public void setMarkup(boolean markup) {
        isMarkup = markup;
    }
}
