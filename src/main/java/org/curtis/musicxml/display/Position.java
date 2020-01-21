package org.curtis.musicxml.display;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "position")
public class Position extends DatabaseItem {
    @Column(name = "default_x", precision = 12, scale = 4)
    private BigDecimal defaultX;
    @Column(name = "default_y", precision = 12, scale = 4)
    private BigDecimal defaultY;
    @Column(name = "relative_x", precision = 12, scale = 4)
    private BigDecimal relativeX;
    @Column(name = "relative_y", precision = 12, scale = 4)
    private BigDecimal relativeY;

    public Position() {

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
