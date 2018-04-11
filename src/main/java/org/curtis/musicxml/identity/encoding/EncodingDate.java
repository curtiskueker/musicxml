package org.curtis.musicxml.identity.encoding;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@DiscriminatorValue("encoding date")
public class EncodingDate extends Encoding {
    @Transient
    private Date encodingDate;

    public EncodingDate() {

    }

    public Date getEncodingDate() {
        return encodingDate;
    }

    public void setEncodingDate(Date encodingDate) {
        this.encodingDate = encodingDate;
    }
}
