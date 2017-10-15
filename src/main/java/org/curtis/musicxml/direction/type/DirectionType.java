package org.curtis.musicxml.direction.type;

import org.curtis.musicxml.attributes.Image;
import org.curtis.musicxml.note.notation.Dynamics;

import java.util.List;

public abstract class DirectionType {
    private List<Dynamics> dynamicsList;
    private Image image;

    public List<Dynamics> getDynamicsList() {
        return dynamicsList;
    }

    public void setDynamicsList(List<Dynamics> dynamicsList) {
        this.dynamicsList = dynamicsList;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
