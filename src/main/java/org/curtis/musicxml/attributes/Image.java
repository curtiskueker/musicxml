package org.curtis.musicxml.attributes;

import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.directiontype.DirectionType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("image")
public class Image extends DirectionType {
    @Transient
    private String source;
    @Transient
    private String type;
    @Transient
    private Position position;
    @Transient
    private Location halign;
    @Transient
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Location getHalign() {
        return halign;
    }

    public void setHalign(Location halign) {
        this.halign = halign;
    }

    public Location getValignImage() {
        return valignImage;
    }

    public void setValignImage(Location valignImage) {
        this.valignImage = valignImage;
    }
}
