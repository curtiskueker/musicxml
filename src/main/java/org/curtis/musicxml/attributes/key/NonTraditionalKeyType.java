package org.curtis.musicxml.attributes.key;

import org.curtis.database.OrderedItem;
import org.curtis.musicxml.converter.AccidentalTypeConverter;
import org.curtis.musicxml.converter.StepConverter;
import org.curtis.musicxml.note.AccidentalType;
import org.curtis.musicxml.note.Step;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "non_traditional_key_type")
public class NonTraditionalKeyType extends OrderedItem {
    @Convert(converter = StepConverter.class)
    @Column(name = "key_step")
    private Step keyStep;
    @Column(name = "key_alter", precision = 12, scale = 4)
    private BigDecimal keyAlter;
    @Convert(converter = AccidentalTypeConverter.class)
    @Column(name = "key_accidental")
    private AccidentalType keyAccidental;
    @Column(name = "key_accidental_smufl")
    private String keyAccidentalSmufl;

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

    public String getKeyAccidentalSmufl() {
        return keyAccidentalSmufl;
    }

    public void setKeyAccidentalSmufl(String keyAccidentalSmufl) {
        this.keyAccidentalSmufl = keyAccidentalSmufl;
    }
}
