package org.curtis.musicxml.layout;

import java.math.BigDecimal;

public class Scaling {
    private BigDecimal millimeters;
    private BigDecimal tenths;

    public Scaling() {

    }

    public BigDecimal getMillimeters() {
        return millimeters;
    }

    public void setMillimeters(BigDecimal millimeters) {
        this.millimeters = millimeters;
    }

    public BigDecimal getTenths() {
        return tenths;
    }

    public void setTenths(BigDecimal tenths) {
        this.tenths = tenths;
    }
}
