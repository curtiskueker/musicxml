package org.curtis.musicxml.barline;

import org.curtis.musicxml.score.MusicData;

public class Barline extends MusicData {
    private BarStyleColor barStyle;
    // TODO: editorial
    // TODO: wavy line
    // TODO: segno print
    // TODO: coda print
    // TODO: fermata
    private Ending ending;
    private Repeat repeat;
    private String location;
    private String segno;
    private String coda;
    // TODO: divisions

    public Barline() {

    }

    public BarStyleColor getBarStyle() {
        return barStyle;
    }

    public void setBarStyle(BarStyleColor barStyle) {
        this.barStyle = barStyle;
    }

    public Ending getEnding() {
        return ending;
    }

    public void setEnding(Ending ending) {
        this.ending = ending;
    }

    public Repeat getRepeat() {
        return repeat;
    }

    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSegno() {
        return segno;
    }

    public void setSegno(String segno) {
        this.segno = segno;
    }

    public String getCoda() {
        return coda;
    }

    public void setCoda(String coda) {
        this.coda = coda;
    }
}
