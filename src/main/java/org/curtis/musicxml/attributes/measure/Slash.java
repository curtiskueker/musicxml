package org.curtis.musicxml.attributes.measure;

public class Slash extends MeasureStyle {
    private SlashGroup slashGroup;
    // TODO: type
    // TODO: use dots
    // TODO: use stems

    public Slash() {

    }

    public SlashGroup getSlashGroup() {
        return slashGroup;
    }

    public void setSlashGroup(SlashGroup slashGroup) {
        this.slashGroup = slashGroup;
    }
}
