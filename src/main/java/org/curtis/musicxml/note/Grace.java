package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Connection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table(name = "grace")
public class Grace extends DatabaseItem {
    @Transient
    private Connection graceType;
    @Column(name = "steal_time_previous")
    private BigDecimal stealTimePrevious;
    @Column(name = "steal_time_following")
    private BigDecimal stealTimeFollowing;
    @Column(name = "make_time")
    private BigDecimal makeTime;
    @Column
    private Boolean slash;

    public Grace() {

    }

    public Connection getGraceType() {
        return graceType;
    }

    public void setGraceType(Connection graceType) {
        this.graceType = graceType;
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
