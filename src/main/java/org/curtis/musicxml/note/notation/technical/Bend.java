package org.curtis.musicxml.note.notation.technical;

import org.curtis.musicxml.converter.BendTypeConverter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("bend")
public class Bend extends Technical {
    @Column(name = "bend_alter", precision = 12, scale = 4)
    private BigDecimal bendAlter;
    @Convert(converter = BendTypeConverter.class)
    @Column
    private BendType type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "with_bar_id")
    private BendWithBar withBar;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bend_sound_id")
    private BendSound bendSound;

    public Bend() {

    }

    public BigDecimal getBendAlter() {
        return bendAlter;
    }

    public void setBendAlter(BigDecimal bendAlter) {
        this.bendAlter = bendAlter;
    }

    public BendType getType() {
        return type;
    }

    public void setType(BendType type) {
        this.type = type;
    }

    public BendWithBar getWithBar() {
        return withBar;
    }

    public void setWithBar(BendWithBar withBar) {
        this.withBar = withBar;
    }

    public BendSound getBendSound() {
        return bendSound;
    }

    public void setBendSound(BendSound bendSound) {
        this.bendSound = bendSound;
    }
}
