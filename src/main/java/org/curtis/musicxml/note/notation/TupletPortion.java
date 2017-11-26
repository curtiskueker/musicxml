package org.curtis.musicxml.note.notation;

import java.util.ArrayList;
import java.util.List;

public class TupletPortion {
    private TupletNumber tupletNumber;
    private TupletType tupletType;
    private List<TupletDot> tupletDots = new ArrayList<>();

    public TupletPortion() {

    }

    public TupletNumber getTupletNumber() {
        return tupletNumber;
    }

    public void setTupletNumber(TupletNumber tupletNumber) {
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
