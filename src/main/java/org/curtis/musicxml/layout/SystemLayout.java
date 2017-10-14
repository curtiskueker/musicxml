package org.curtis.musicxml.layout;

import java.math.BigDecimal;

public class SystemLayout {
    private SystemMargins systemMargins;
    private BigDecimal systemDistance;
    private BigDecimal topSystemDistance;
    private SystemDividers systemDividers;

    public SystemLayout() {

    }

    public SystemMargins getSystemMargins() {
        return systemMargins;
    }

    public void setSystemMargins(SystemMargins systemMargins) {
        this.systemMargins = systemMargins;
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
