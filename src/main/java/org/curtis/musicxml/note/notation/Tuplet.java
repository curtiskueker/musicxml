package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.LineShape;

public class Tuplet extends Notation {
    private TupletPortion tupletActual;
    private TupletPortion tupletNormal;
    private Connection type;
    private Integer number;
    private Boolean bracket;
    private ShowTuplet showNumber;
    private ShowTuplet showType;
    private LineShape lineShape;
    private Position position;
    private Location placement;

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

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
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

    public ShowTuplet getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(ShowTuplet showNumber) {
        this.showNumber = showNumber;
    }

    public ShowTuplet getShowType() {
        return showType;
    }

    public void setShowType(ShowTuplet showType) {
        this.showType = showType;
    }

    public LineShape getLineShape() {
        return lineShape;
    }

    public void setLineShape(LineShape lineShape) {
        this.lineShape = lineShape;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Location getPlacement() {
        return placement;
    }

    public void setPlacement(Location placement) {
        this.placement = placement;
    }
}
