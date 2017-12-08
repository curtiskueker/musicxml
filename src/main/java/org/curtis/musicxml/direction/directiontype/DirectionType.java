package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.attributes.Image;
import org.curtis.musicxml.score.MusicData;

public abstract class DirectionType extends MusicData {
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
