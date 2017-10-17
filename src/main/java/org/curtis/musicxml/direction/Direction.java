package org.curtis.musicxml.direction;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Level;
import org.curtis.musicxml.direction.type.DirectionType;
import org.curtis.musicxml.score.MusicData;

import java.util.List;

public class Direction extends MusicData {
    private List<DirectionType> directionTypes;
    private Offset offset;
    private FormattedText footnote;
    private Level level;
    private String voice;
    private Integer staff;
    private Sound sound;
    private Location placement;
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

    public FormattedText getFootnote() {
        return footnote;
    }

    public void setFootnote(FormattedText footnote) {
        this.footnote = footnote;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
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
