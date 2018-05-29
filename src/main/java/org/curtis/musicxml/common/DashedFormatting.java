package org.curtis.musicxml.common;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "dashed_formatting")
public class DashedFormatting extends DatabaseItem {
    @Column(name = "dash_length")
    private BigDecimal dashLength;
    @Column(name = "space_length")
    private BigDecimal spaceLength;

    public DashedFormatting() {

    }

    public BigDecimal getDashLength() {
        return dashLength;
    }

    public void setDashLength(BigDecimal dashLength) {
        this.dashLength = dashLength;
    }

    public BigDecimal getSpaceLength() {
        return spaceLength;
    }

    public void setSpaceLength(BigDecimal spaceLength) {
        this.spaceLength = spaceLength;
    }
}
