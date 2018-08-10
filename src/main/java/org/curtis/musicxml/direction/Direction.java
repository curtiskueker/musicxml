package org.curtis.musicxml.direction;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.score.MusicData;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("direction")
public class Direction extends MusicData {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "direction_id", nullable = false)
    private List<DirectionType> directionTypes = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offset_id")
    private Offset offset;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_voice_direction_id")
    private EditorialVoiceDirection editorialVoiceDirection;
    @Column
    private Integer staff;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sound_id")
    private Sound sound;
    @Enumerated(EnumType.STRING)
    @Column
    private Location placement;
    @Column
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
