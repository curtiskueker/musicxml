package org.curtis.musicxml.attributes.measure;

import org.curtis.musicxml.common.Connection;

public class Slash extends MeasureStyle {
    private SlashGroup slashGroup;
    private Connection type;
    private Boolean useDots;
    private Boolean useStems;

    public Slash() {

    }

    public SlashGroup getSlashGroup() {
        return slashGroup;
    }

    public void setSlashGroup(SlashGroup slashGroup) {
        this.slashGroup = slashGroup;
    }

    public Connection getType() {
        return type;
    }

    public void setType(Connection type) {
        this.type = type;
    }

    public Boolean getUseDots() {
        return useDots;
    }

    public void setUseDots(Boolean useDots) {
        this.useDots = useDots;
    }

    public Boolean getUseStems() {
        return useStems;
    }

    public void setUseStems(Boolean useStems) {
        this.useStems = useStems;
    }
}
