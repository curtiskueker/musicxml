package org.curtis.musicxml.direction;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.score.MusicData;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("direction")
public class Direction extends MusicData {
    @Transient
    private List<DirectionType> directionTypes = new ArrayList<>();
    @Transient
    private Offset offset;
    @Transient
    private EditorialVoiceDirection editorialVoiceDirection;
    @Transient
    private Integer staff;
    @Transient
    private Sound sound;
    @Transient
    private Location placement;
    @Transient
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

    public EditorialVoiceDirection getEditorialVoiceDirection() {
        return editorialVoiceDirection;
    }

    public void setEditorialVoiceDirection(EditorialVoiceDirection editorialVoiceDirection) {
        this.editorialVoiceDirection = editorialVoiceDirection;
    }

    public Integer getStaff() {
        return staff;
    }

    public void setStaff(Integer staff) {
        this.staff = staff;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public Location getPlacement() {
        return placement;
    }

    public void setPlacement(Location placement) {
        this.placement = placement;
    }

    public Boolean getDirective() {
        return directive;
    }

    public void setDirective(Boolean directive) {
        this.directive = directive;
    }
}
