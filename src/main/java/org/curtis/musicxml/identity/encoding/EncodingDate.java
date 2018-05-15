package org.curtis.musicxml.identity.encoding;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("encoding date")
public class EncodingDate extends Encoding {
    @Column(name = "encoding_date")
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
