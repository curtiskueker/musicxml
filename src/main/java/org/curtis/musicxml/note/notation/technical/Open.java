package org.curtis.musicxml.note.notation.technical;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("open")
public class Open extends Technical {
    @Column
    private String smufl;

    public Open() {

    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
