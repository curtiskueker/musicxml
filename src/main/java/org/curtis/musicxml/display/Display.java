package org.curtis.musicxml.display;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "display")
public class Display extends DatabaseItem {
    @Column(name = "default_x", precision = 12, scale = 4)
    private BigDecimal defaultX;
    @Column(name = "default_y", precision = 12, scale = 4)
    private BigDecimal defaultY;
    @Column(name = "relative_x", precision = 12, scale = 4)
    private BigDecimal relativeX;
    @Column(name = "relative_y", precision = 12, scale = 4)
    private BigDecimal relativeY;
    @Enumerated(EnumType.STRING)
    @Column
    private Placement placement;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "font_id")
    private Font font;
    @Column
    private String color;
    @Enumerated(EnumType.STRING)
    @Column
    private Halign halign;
    @Enumerated(EnumType.STRING)
    @Column
    private Valign valign;

    public Display() {

    }

    public BigDecimal getDefaultX() {
        return defaultX;
    }

    public void setDefaultX(BigDecimal defaultX) {
        this.defaultX = defaultX;
    }

    public BigDecimal getDefaultY() {
        return defaultY;
    }

    public void setDefaultY(BigDecimal defaultY) {
        this.defaultY = defaultY;
    }

    public BigDecimal getRelativeX() {
        return relativeX;
    }

    public void setRelativeX(BigDecimal relativeX) {
        this.relativeX = relativeX;
    }

    public BigDecimal getRelativeY() {
        return relativeY;
    }

    public void setRelativeY(BigDecimal relativeY) {
        this.relativeY = relativeY;
    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Halign getHalign() {
        return halign;
    }

    public void setHalign(Halign halign) {
        this.halign = halign;
    }

    public Valign getValign() {
        return valign;
    }

    public void setValign(Valign valign) {
        this.valign = valign;
    }
}
