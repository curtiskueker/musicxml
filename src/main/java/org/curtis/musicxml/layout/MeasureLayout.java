package org.curtis.musicxml.layout;

import org.curtis.database.DatabaseItem;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table(name = "measure_layout")
public class MeasureLayout extends DatabaseItem {
    @Transient
    private BigDecimal measureDistance;

    public MeasureLayout() {

    }

    public BigDecimal getMeasureDistance() {
        return measureDistance;
    }

    public void setMeasureDistance(BigDecimal measureDistance) {
        this.measureDistance = measureDistance;
    }
}
