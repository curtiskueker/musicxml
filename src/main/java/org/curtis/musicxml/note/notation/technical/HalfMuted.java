package org.curtis.musicxml.note.notation.technical;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("half muted")
public class HalfMuted extends Technical {
    @Column
    private String smufl;

    public HalfMuted() {

    }

    public String getSmufl() {
        return smufl;
    }

    public void setSmufl(String smufl) {
        this.smufl = smufl;
    }
}
