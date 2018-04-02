package org.curtis.musicxml.attributes.time;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("senza misura")
public class SenzaMisura extends Time {
    @Column
    private String value;

    public SenzaMisura() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
