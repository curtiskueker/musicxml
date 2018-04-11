package org.curtis.musicxml.identity.encoding;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("software")
public class Software extends Encoding {
    @Column
    private String software;

    public Software() {

    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }
}
