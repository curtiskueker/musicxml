package org.curtis.musicxml.note.notation.technical;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("fingering")
public class Fingering extends Technical {
    @Column
    private String value;
    @Column
    @Type(type="yes_no")
    private Boolean substitution;
    @Column
    @Type(type="yes_no")
    private Boolean alternate;

    public Fingering() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getSubstitution() {
        return substitution;
    }

    public void setSubstitution(Boolean substitution) {
        this.substitution = substitution;
    }

    public Boolean getAlternate() {
        return alternate;
    }

    public void setAlternate(Boolean alternate) {
        this.alternate = alternate;
    }
}
