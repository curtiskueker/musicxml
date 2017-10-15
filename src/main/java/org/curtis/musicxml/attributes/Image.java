package org.curtis.musicxml.attributes;

import org.curtis.musicxml.common.Position;

public class Image {
    private String source;
    private String type;
    private Position position;
    private String halign;
    private String valignImage;

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

    public String getHalign() {
        return halign;
    }

    public void setHalign(String halign) {
        this.halign = halign;
    }

    public String getValignImage() {
        return valignImage;
    }

    public void setValignImage(String valignImage) {
        this.valignImage = valignImage;
    }
}
