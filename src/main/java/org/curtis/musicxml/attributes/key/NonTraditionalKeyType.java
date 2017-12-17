package org.curtis.musicxml.attributes.key;

import org.curtis.musicxml.note.AccidentalType;
import org.curtis.musicxml.note.Step;

import java.math.BigDecimal;

public class NonTraditionalKeyType {
    private Step keyStep;
    private BigDecimal keyAlter;
    private AccidentalType keyAccidental;

    public NonTraditionalKeyType() {

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

    public AccidentalType getKeyAccidental() {
        return keyAccidental;
    }

    public void setKeyAccidental(AccidentalType keyAccidental) {
        this.keyAccidental = keyAccidental;
    }
}
