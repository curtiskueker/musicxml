package org.curtis.musicxml.direction;

import org.curtis.musicxml.direction.type.DirectionType;
import org.curtis.musicxml.score.MusicData;

import java.util.List;

public class Direction extends MusicData {
    private List<DirectionType> directionTypes;
    private Offset offset;
    // TODO: footnote
    // TODO: level
    // TODO: voice
    // TODO: staff
    private Sound sound;
    private String placement;
    private Boolean directive;

    public Direction() {

    }

    public List<DirectionType> getDirectionTypes() {
        return directionTypes;
    }

    public void setDirectionTypes(List<DirectionType> directionTypes) {
        this.directionTypes = directionTypes;
    }

    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }

    public Boolean getDirective() {
        return directive;
    }

    public void setDirective(Boolean directive) {
        this.directive = directive;
    }
}
