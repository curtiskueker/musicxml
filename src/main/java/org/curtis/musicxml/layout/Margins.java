package org.curtis.musicxml.layout;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "margins")
public class Margins extends DatabaseItem {
    @Column(name = "left_margin", precision = 10, scale = 4)
    private BigDecimal leftMargin;
    @Column(name = "right_margin", precision = 10, scale = 4)
    private BigDecimal rightMargin;
    @Column(name = "top_margin", precision = 10, scale = 4)
    private BigDecimal topMargin;
    @Column(name = "bottom_margin", precision = 10, scale = 4)
    private BigDecimal bottomMargin;

    public Margins() {

    }

    public BigDecimal getLeftMargin() {
        return leftMargin;
    }

    public void setLeftMargin(BigDecimal leftMargin) {
        this.leftMargin = leftMargin;
    }

    public BigDecimal getRightMargin() {
        return rightMargin;
    }

    public void setRightMargin(BigDecimal rightMargin) {
        this.rightMargin = rightMargin;
    }

    public BigDecimal getTopMargin() {
        return topMargin;
    }

    public void setTopMargin(BigDecimal topMargin) {
        this.topMargin = topMargin;
    }

    public BigDecimal getBottomMargin() {
        return bottomMargin;
    }

    public void setBottomMargin(BigDecimal bottomMargin) {
        this.bottomMargin = bottomMargin;
    }
}
