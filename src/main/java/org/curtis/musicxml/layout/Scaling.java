package org.curtis.musicxml.layout;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "scaling")
public class Scaling extends DatabaseItem {
    @Column(precision = 10, scale = 4)
    private BigDecimal millimeters;
    @Column(precision = 10, scale = 4)
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
