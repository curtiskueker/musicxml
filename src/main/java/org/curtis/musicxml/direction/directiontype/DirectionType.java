package org.curtis.musicxml.direction.directiontype;

import org.curtis.musicxml.attributes.Image;
import org.curtis.musicxml.score.MusicData;

import java.util.Arrays;
import java.util.List;

public abstract class DirectionType extends MusicData {
    public static List<String> MULTIPLE_DIRECTION_TYPES = Arrays.asList("Dynamics", "Wedge");

    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
