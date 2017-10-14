package org.curtis.musicxml.layout;

import java.math.BigDecimal;

public class Distance {
    private BigDecimal value;
    private String type;

    public Distance() {

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
