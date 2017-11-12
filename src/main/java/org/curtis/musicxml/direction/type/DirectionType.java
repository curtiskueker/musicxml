package org.curtis.musicxml.direction.type;

import org.curtis.musicxml.attributes.Image;

public abstract class DirectionType {
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
