package org.curtis.musicxml.common.play;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ipa")
public class Ipa extends PlayType {
    @Column
    private String value;

    public Ipa() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
