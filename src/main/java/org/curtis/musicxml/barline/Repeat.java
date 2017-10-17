package org.curtis.musicxml.barline;

public class Repeat {
    private RepeatDirection direction;
    private Integer times;
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
