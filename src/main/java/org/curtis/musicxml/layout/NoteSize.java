package org.curtis.musicxml.layout;

import java.math.BigDecimal;

public class NoteSize {
    private BigDecimal value;
    private String type;

    public NoteSize() {

    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
