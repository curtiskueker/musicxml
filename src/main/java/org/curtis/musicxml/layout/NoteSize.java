package org.curtis.musicxml.layout;

import java.math.BigDecimal;

public class NoteSize {
    private BigDecimal value;
    private NoteSizeType type;

    public NoteSize() {

    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public NoteSizeType getType() {
        return type;
    }

    public void setType(NoteSizeType type) {
        this.type = type;
    }
}
