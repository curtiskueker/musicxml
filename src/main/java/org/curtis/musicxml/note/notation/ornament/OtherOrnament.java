package org.curtis.musicxml.note.notation.ornament;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("other ornament")
public class OtherOrnament extends Ornament {
    @Column
    private String value;
    @Column
    private String smufl;

    public OtherOrnament() {

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
