package org.curtis.musicxml.identity.encoding;

import org.curtis.musicxml.identity.IdentificationType;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("encoder")
public class Encoder extends Encoding {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "encoder_id")
    private IdentificationType identificationType;

    public Encoder() {

    }

    public IdentificationType getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(IdentificationType identificationType) {
        this.identificationType = identificationType;
    }
}
