package org.curtis.musicxml.attributes.measure;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.converter.ConnectionConverter;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("beat repeat")
public class BeatRepeat extends MeasureStyle {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "slash_group_id")
    private SlashGroup slashGroup;
    @Convert(converter = ConnectionConverter.class)
    @Column
    private Connection type;
    @Column
    private Integer slashes;
    @Column(name = "use_dots")
    @Type(type="yes_no")
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
