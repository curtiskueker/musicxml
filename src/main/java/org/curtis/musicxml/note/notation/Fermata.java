package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.converter.FermataShapeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("fermata")
public class Fermata extends Notation {
    @Column(name = "fermata_shape")
    @Convert(converter = FermataShapeConverter.class)
    private FermataShape fermataShape;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_value")
    private FermataType type;
    @ManyToOne
    @JoinColumn(name = "barline_id")
    private Barline barline;
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

    public Barline getBarline() {
        return barline;
    }

    public void setBarline(Barline barline) {
        this.barline = barline;
    }

    public boolean isMarkup() {
        return isMarkup;
    }

    public void setMarkup(boolean markup) {
        isMarkup = markup;
    }
}
