package org.curtis.musicxml.note;

import java.math.BigDecimal;

public class Grace {
    private BigDecimal stealTimePrevious;
    private BigDecimal stealTimeFollowing;
    private BigDecimal makeTime;
    private Boolean slash;

    public Grace() {

    }

    public BigDecimal getStealTimePrevious() {
        return stealTimePrevious;
    }

    public void setStealTimePrevious(BigDecimal stealTimePrevious) {
        this.stealTimePrevious = stealTimePrevious;
    }

    public BigDecimal getStealTimeFollowing() {
        return stealTimeFollowing;
    }

    public void setStealTimeFollowing(BigDecimal stealTimeFollowing) {
        this.stealTimeFollowing = stealTimeFollowing;
    }

    public BigDecimal getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(BigDecimal makeTime) {
        this.makeTime = makeTime;
    }

    public Boolean getSlash() {
        return slash;
    }

    public void setSlash(Boolean slash) {
        this.slash = slash;
    }
}
