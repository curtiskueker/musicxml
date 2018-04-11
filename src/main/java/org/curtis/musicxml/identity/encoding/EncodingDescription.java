package org.curtis.musicxml.identity.encoding;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("encoding description")
public class EncodingDescription extends Encoding {
    @Column(name = "encoding_description")
    private String encodingDescription;

    public EncodingDescription() {

    }

    public String getEncodingDescription() {
        return encodingDescription;
    }

    public void setEncodingDescription(String encodingDescription) {
        this.encodingDescription = encodingDescription;
    }
}
