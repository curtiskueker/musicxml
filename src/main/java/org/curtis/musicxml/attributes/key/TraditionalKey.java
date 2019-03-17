package org.curtis.musicxml.attributes.key;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("traditional key")
public class TraditionalKey extends Key {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cancel_id")
    private Cancel cancel;
    @Column
    private Integer fifths;
    @Column(name = "key_mode")
    private String mode;

    public TraditionalKey() {

    }

    public Cancel getCancel() {
        return cancel;
    }

    public void setCancel(Cancel cancel) {
        this.cancel = cancel;
    }

    public Integer getFifths() {
        return fifths;
    }

    public void setFifths(Integer fifths) {
        this.fifths = fifths;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
