package org.curtis.musicxml.attributes.measure;

import org.curtis.musicxml.common.Connection;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("slash")
public class Slash extends MeasureStyle {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "slash_group_id")
    private SlashGroup slashGroup;
    @Enumerated(EnumType.STRING)
    @Column
    private Connection type;
    @Column(name = "use_dots")
    @Type(type="yes_no")
    private Boolean useDots;
    @Column(name = "use_stems")
    @Type(type="yes_no")
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
