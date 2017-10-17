package org.curtis.musicxml.attributes.key;

import org.curtis.musicxml.note.AccidentalValue;
import org.curtis.musicxml.note.Step;

import java.math.BigDecimal;

public class NonTraditionalKey {
    private Step keyStep;
    private BigDecimal keyAlter;
    private AccidentalValue keyAccidental;

    public NonTraditionalKey() {

    }

    public Step getKeyStep() {
        return keyStep;
    }

    public void setKeyStep(Step keyStep) {
        this.keyStep = keyStep;
    }

    public BigDecimal getKeyAlter() {
        return keyAlter;
    }

    public void setKeyAlter(BigDecimal keyAlter) {
        this.keyAlter = keyAlter;
    }

    public AccidentalValue getKeyAccidental() {
        return keyAccidental;
    }

    public void setKeyAccidental(AccidentalValue keyAccidental) {
        this.keyAccidental = keyAccidental;
    }
}
