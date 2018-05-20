package org.curtis.musicxml.note.notation;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.LineShape;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("tuplet")
public class Tuplet extends Notation {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tuplet_actual_id")
    private TupletPortion tupletActual;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tuplet_normal_id")
    private TupletPortion tupletNormal;
    @Enumerated(EnumType.STRING)
    @Column
    private Connection type;
    @Column
    private Integer number;
    @Column
    private Boolean bracket;
    @Enumerated(EnumType.STRING)
    @Column(name = "show_number")
    private ShowTuplet showNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "show_type")
    private ShowTuplet showType;
    @Enumerated(EnumType.STRING)
    @Column(name = "line_shape")
    private LineShape lineShape;
    @Transient
    private Position position;
    @Transient
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
