package org.curtis.musicxml.note.notation.technical;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("other technical")
public class OtherTechnical extends Technical {
    @Column
    private String value;
    @Column
    private String smufl;

    public OtherTechnical() {

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
