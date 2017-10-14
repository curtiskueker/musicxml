package org.curtis.musicxml.attributes.measure;

public class Slash extends MeasureStyle {
    private SlashGroup slashGroup;
    private String type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
