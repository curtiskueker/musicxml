package org.curtis.musicxml.layout;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table(name = "system_layout")
public class SystemLayout extends DatabaseItem {
    @Transient
    private BigDecimal leftMargin;
    @Transient
    private BigDecimal rightMargin;
    @Transient
    private BigDecimal systemDistance;
    @Transient
    private BigDecimal topSystemDistance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "system_dividers_id")
    private SystemDividers systemDividers;

    public SystemLayout() {

    }

    public BigDecimal getLeftMargin() {
        return leftMargin;
    }

    public void setLeftMargin(BigDecimal leftMargin) {
        this.leftMargin = leftMargin;
    }

    public BigDecimal getRightMargin() {
        return rightMargin;
    }

    public void setRightMargin(BigDecimal rightMargin) {
        this.rightMargin = rightMargin;
    }

    public BigDecimal getSystemDistance() {
        return systemDistance;
    }

    public void setSystemDistance(BigDecimal systemDistance) {
        this.systemDistance = systemDistance;
    }

    public BigDecimal getTopSystemDistance() {
        return topSystemDistance;
    }

    public void setTopSystemDistance(BigDecimal topSystemDistance) {
        this.topSystemDistance = topSystemDistance;
    }

    public SystemDividers getSystemDividers() {
        return systemDividers;
    }

    public void setSystemDividers(SystemDividers systemDividers) {
        this.systemDividers = systemDividers;
    }
}
