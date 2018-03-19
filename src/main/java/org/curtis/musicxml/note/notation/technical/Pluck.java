package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.note.PlacementText;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("pluck")
public class Pluck extends Technical {
    @Transient
    private PlacementText value;

    public Pluck() {

    }

    public PlacementText getValue() {
        return value;
    }

    public void setValue(PlacementText value) {
        this.value = value;
    }
}
