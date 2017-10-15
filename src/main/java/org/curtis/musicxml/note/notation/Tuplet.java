package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.Position;

public class Tuplet extends Notation {
    private TupletPortion tupletActual;
    private TupletPortion tupletNormal;
    private String type;
    private Integer number;
    private Boolean bracket;
    private String showNumber;
    private String showType;
    private String lineShape;
    private Position position;
    private String placement;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getBracket() {
        return bracket;
    }

    public void setBracket(Boolean bracket) {
        this.bracket = bracket;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}
