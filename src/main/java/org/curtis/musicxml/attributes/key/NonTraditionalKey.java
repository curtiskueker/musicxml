package org.curtis.musicxml.attributes.key;

import java.math.BigDecimal;

public class NonTraditionalKey {
    private String keyStep;
    private BigDecimal keyAlter;
    private String keyAccidental;

    public NonTraditionalKey() {

    }

    public String getKeyStep() {
        return keyStep;
    }

    public void setKeyStep(String keyStep) {
        this.keyStep = keyStep;
    }

    public BigDecimal getKeyAlter() {
        return keyAlter;
    }

    public void setKeyAlter(BigDecimal keyAlter) {
        this.keyAlter = keyAlter;
    }

    public String getKeyAccidental() {
        return keyAccidental;
    }

    public void setKeyAccidental(String keyAccidental) {
        this.keyAccidental = keyAccidental;
    }
}
