package org.curtis.musicxml.attributes.key;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.note.AccidentalType;
import org.curtis.musicxml.note.Step;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table(name = "non_traditional_key_type")
public class NonTraditionalKeyType extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column(name = "key_step")
    private Step keyStep;
    @Column(name = "key_alter")
    private BigDecimal keyAlter;
    @Transient
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
