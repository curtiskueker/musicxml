package org.curtis.musicxml.identity.encoding;

import org.curtis.musicxml.identity.TypedText;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("encoding")
public class Encoder extends Encoding {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "encoder_id")
    private TypedText encoder;

    public Encoder() {

    }

    public TypedText getEncoder() {
        return encoder;
    }

    public void setEncoder(TypedText encoder) {
        this.encoder = encoder;
    }
}
