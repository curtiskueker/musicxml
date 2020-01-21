package org.curtis.musicxml.attributes;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.directiontype.DirectionType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("image")
public class Image extends DirectionType {
    @Column
    private String source;
    @Column(name = "direction_type")
    private String type;
    @Column(precision = 12, scale = 4)
    private BigDecimal height;
    @Column(precision = 12, scale = 4)
    private BigDecimal width;
    @Enumerated(EnumType.STRING)
    @Column(name = "valign_image")
    private Location valignImage;

    public Image() {

    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public Location getValignImage() {
        return valignImage;
    }

    public void setValignImage(Location valignImage) {
        this.valignImage = valignImage;
    }
}
