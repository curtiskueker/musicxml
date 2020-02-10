package org.curtis.musicxml.direction;

import org.curtis.musicxml.direction.directiontype.DirectionTypeList;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.display.Editorial;
import org.curtis.musicxml.display.Placement;
import org.curtis.musicxml.score.MusicData;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("direction")
public class Direction extends MusicData {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "direction_id")
    private List<DirectionTypeList> directionTypeLists = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offset_id")
    private DirectionOffset offset;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;
    @Column
    private Integer staff;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sound_id")
    private Sound sound;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "display_id")
    private Display display;
    @Column
    @Type(type="yes_no")
    private Boolean directive;

    public Direction() {

    }

    public List<DirectionTypeList> getDirectionTypeLists() {
        return directionTypeLists;
    }

    public void setDirectionTypeLists(List<DirectionTypeList> directionTypeLists) {
        this.directionTypeLists = directionTypeLists;
    }

    public DirectionOffset getOffset() {
        return offset;
    }

    public void setOffset(DirectionOffset offset) {
        this.offset = offset;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
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

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Placement getPlacement() {
        if (display == null) return null;
        return display.getPlacement();
    }

    public Boolean getDirective() {
        return directive;
    }

    public void setDirective(Boolean directive) {
        this.directive = directive;
    }
}
