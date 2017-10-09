package org.curtis.musicxml.identity.encoding;

import org.curtis.musicxml.identity.TypedText;

public class Encoder extends Encoding {
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
