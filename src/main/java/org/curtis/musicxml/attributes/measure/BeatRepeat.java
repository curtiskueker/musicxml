package org.curtis.musicxml.attributes.measure;

import org.curtis.musicxml.common.Connection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@DiscriminatorValue("beat repeat")
public class BeatRepeat extends MeasureStyle {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "slash_group_id")
    private SlashGroup slashGroup;
    @Enumerated(EnumType.STRING)
    @Column
    private Connection type;
    @Column
    private Integer slashes;
    @Transient
    private Boolean useDots;

    public BeatRepeat() {

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

    public Integer getSlashes() {
        return slashes;
    }

    public void setSlashes(Integer slashes) {
        this.slashes = slashes;
    }

    public Boolean getUseDots() {
        return useDots;
    }

    public void setUseDots(Boolean useDots) {
        this.useDots = useDots;
    }
}
