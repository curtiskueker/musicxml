package org.curtis.musicxml.barline;

public class Repeat {
    private String direction;
    private Integer times;
    private String winged;

    public Repeat() {

    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getWinged() {
        return winged;
    }

    public void setWinged(String winged) {
        this.winged = winged;
    }
}
