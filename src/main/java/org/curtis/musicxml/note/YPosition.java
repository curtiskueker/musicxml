package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table(name = "y_position")
public class YPosition extends DatabaseItem {
    @Transient
    private BigDecimal defaultX;
    @Transient
    private BigDecimal defaultY;
    @Transient
    private BigDecimal relativeX;
    @Transient
    private BigDecimal relativeY;

    public YPosition() {

    }

    public BigDecimal getDefaultX() {
        return defaultX;
    }

    public void setDefaultX(BigDecimal defaultX) {
        this.defaultX = defaultX;
    }

    public BigDecimal getDefaultY() {
        return defaultY;
    }

    public void setDefaultY(BigDecimal defaultY) {
        this.defaultY = defaultY;
    }

    public BigDecimal getRelativeX() {
        return relativeX;
    }

    public void setRelativeX(BigDecimal relativeX) {
        this.relativeX = relativeX;
    }

    public BigDecimal getRelativeY() {
        return relativeY;
    }

    public void setRelativeY(BigDecimal relativeY) {
        this.relativeY = relativeY;
    }
}
