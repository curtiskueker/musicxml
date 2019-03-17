package org.curtis.musicxml.note;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.common.Connection;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table(name = "grace")
public class Grace extends DatabaseItem {
    @Column(name = "steal_time_previous", precision = 12, scale = 4)
    private BigDecimal stealTimePrevious;
    @Column(name = "steal_time_following", precision = 12, scale = 4)
    private BigDecimal stealTimeFollowing;
    @Column(name = "make_time", precision = 12, scale = 4)
    private BigDecimal makeTime;
    @Column
    @Type(type="yes_no")
    private Boolean slash;
    @Transient
    // used by lilypond
    private Connection graceType;

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

    public Connection getGraceType() {
        return graceType;
    }

    public void setGraceType(Connection graceType) {
        this.graceType = graceType;
    }
}
