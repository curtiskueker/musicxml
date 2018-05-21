package org.curtis.musicxml.note.notation;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Table(name = "bezier")
public class Bezier extends DatabaseItem {
    @Transient
    private BigDecimal bezierOffset;
    @Transient
    private BigDecimal bezierOffset2;
    @Column(name = "bezier_x")
    private BigDecimal bezierX;
    @Column(name = "bezier_y")
    private BigDecimal bezierY;
    @Column(name = "bezier_x2")
    private BigDecimal bezierX2;
    @Column(name = "bezier_y2")
    private BigDecimal bezierY2;

    public Bezier() {

    }

    public BigDecimal getBezierOffset() {
        return bezierOffset;
    }

    public void setBezierOffset(BigDecimal bezierOffset) {
        this.bezierOffset = bezierOffset;
    }

    public BigDecimal getBezierOffset2() {
        return bezierOffset2;
    }

    public void setBezierOffset2(BigDecimal bezierOffset2) {
        this.bezierOffset2 = bezierOffset2;
    }

    public BigDecimal getBezierX() {
        return bezierX;
    }

    public void setBezierX(BigDecimal bezierX) {
        this.bezierX = bezierX;
    }

    public BigDecimal getBezierY() {
        return bezierY;
    }

    public void setBezierY(BigDecimal bezierY) {
        this.bezierY = bezierY;
    }

    public BigDecimal getBezierX2() {
        return bezierX2;
    }

    public void setBezierX2(BigDecimal bezierX2) {
        this.bezierX2 = bezierX2;
    }

    public BigDecimal getBezierY2() {
        return bezierY2;
    }

    public void setBezierY2(BigDecimal bezierY2) {
        this.bezierY2 = bezierY2;
    }
}
