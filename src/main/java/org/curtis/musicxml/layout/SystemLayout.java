package org.curtis.musicxml.layout;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "system_layout")
public class SystemLayout extends DatabaseItem {
    @Column(name = "left_margin", precision = 12, scale = 4)
    private BigDecimal leftMargin;
    @Column(name = "right_margin", precision = 12, scale = 4)
    private BigDecimal rightMargin;
    @Column(name = "system_distance", precision = 12, scale = 4)
    private BigDecimal systemDistance;
    @Column(name = "top_system_distance", precision = 12, scale = 4)
    private BigDecimal topSystemDistance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "left_system_divider_id")
    private SystemDivider leftSystemDivider;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "right_system_divider_id")
    private SystemDivider rightSystemDivider;

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

    public SystemDivider getLeftSystemDivider() {
        return leftSystemDivider;
    }

    public void setLeftSystemDivider(SystemDivider leftSystemDivider) {
        this.leftSystemDivider = leftSystemDivider;
    }

    public SystemDivider getRightSystemDivider() {
        return rightSystemDivider;
    }

    public void setRightSystemDivider(SystemDivider rightSystemDivider) {
        this.rightSystemDivider = rightSystemDivider;
    }

    public boolean hasSystemDividers() {
        return leftSystemDivider != null && rightSystemDivider != null;
    }
}
