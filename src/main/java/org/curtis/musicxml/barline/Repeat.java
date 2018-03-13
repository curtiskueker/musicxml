package org.curtis.musicxml.barline;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "barline_repeat")
public class Repeat extends DatabaseItem {
    @Enumerated(EnumType.STRING)
    @Column
    private RepeatDirection direction;
    @Column
    private Integer times;
    @Enumerated(EnumType.STRING)
    @Column
    private Winged winged;

    public Repeat() {

    }

    public RepeatDirection getDirection() {
        return direction;
    }

    public void setDirection(RepeatDirection direction) {
        this.direction = direction;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Winged getWinged() {
        return winged;
    }

    public void setWinged(Winged winged) {
        this.winged = winged;
    }
}
