package org.curtis.musicxml.note.notation;

import java.util.List;

public class TupletPortion {
    private Integer tupletNumber;
    private TupletType tupletType;
    private List<TupletDot> tupletDots;

    public TupletPortion() {

    }

    public Integer getTupletNumber() {
        return tupletNumber;
    }

    public void setTupletNumber(Integer tupletNumber) {
        this.tupletNumber = tupletNumber;
    }

    public TupletType getTupletType() {
        return tupletType;
    }

    public void setTupletType(TupletType tupletType) {
        this.tupletType = tupletType;
    }

    public List<TupletDot> getTupletDots() {
        return tupletDots;
    }

    public void setTupletDots(List<TupletDot> tupletDots) {
        this.tupletDots = tupletDots;
    }
}
