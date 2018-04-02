package org.curtis.musicxml.attributes.measure;

import org.curtis.musicxml.common.Connection;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("slash")
public class Slash extends MeasureStyle {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "slash_group_id")
    private SlashGroup slashGroup;
    @Transient
    private Connection type;
    @Transient
    private Boolean useDots;
    @Transient
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
