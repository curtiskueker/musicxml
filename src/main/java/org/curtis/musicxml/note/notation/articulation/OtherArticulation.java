package org.curtis.musicxml.note.notation.articulation;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("other articulation")
public class OtherArticulation extends Articulation {
    @Column
    private String value;
    @Column
    private String smufl;

    public OtherArticulation() {

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
