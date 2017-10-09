package org.curtis.musicxml.attributes.measure;

public class BeatRepeat extends MeasureStyle {
    private SlashGroup slashGroup;
    // TODO: type;
    private Integer slashes;
    // TODO: use dots

    public BeatRepeat() {

    }

    public SlashGroup getSlashGroup() {
        return slashGroup;
    }

    public void setSlashGroup(SlashGroup slashGroup) {
        this.slashGroup = slashGroup;
    }

    public Integer getSlashes() {
        return slashes;
    }

    public void setSlashes(Integer slashes) {
        this.slashes = slashes;
    }
}
