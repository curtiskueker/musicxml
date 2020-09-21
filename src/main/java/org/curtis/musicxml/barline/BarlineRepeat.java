package org.curtis.musicxml.barline;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.converter.BarlineRepeatDirectionConverter;
import org.curtis.musicxml.converter.WingedConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "barline_repeat")
public class BarlineRepeat extends DatabaseItem {
    @Convert(converter = BarlineRepeatDirectionConverter.class)
    @Column
    private BarlineRepeatDirection direction;
    @Column
    private Integer times;
    @Convert(converter = WingedConverter.class)
    @Column
    private Winged winged;

    public BarlineRepeat() {

    }

    public BarlineRepeatDirection getDirection() {
        return direction;
    }

    public void setDirection(BarlineRepeatDirection direction) {
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
