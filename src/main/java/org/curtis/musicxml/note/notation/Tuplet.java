package org.curtis.musicxml.note.notation;

public class Tuplet extends Notation {
    private TupletPortion tupletActual;
    private TupletPortion tupletNormal;
    private String type;
    // TODO: number
    // TODO: bracket
    private String showNumber;
    private String showType;
    private String lineShape;
    // TODO: position
    // TODO: placement

    public Tuplet() {

    }

    public TupletPortion getTupletActual() {
        return tupletActual;
    }

    public void setTupletActual(TupletPortion tupletActual) {
        this.tupletActual = tupletActual;
    }

    public TupletPortion getTupletNormal() {
        return tupletNormal;
    }

    public void setTupletNormal(TupletPortion tupletNormal) {
        this.tupletNormal = tupletNormal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(String showNumber) {
        this.showNumber = showNumber;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getLineShape() {
        return lineShape;
    }

    public void setLineShape(String lineShape) {
        this.lineShape = lineShape;
    }
}
