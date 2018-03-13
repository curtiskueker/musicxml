package org.curtis.musicxml.direction.harmony;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Bass extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bass_step_id")
    private BassStep bassStep;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bass_alter_id")
    private BassAlter bassAlter;

    public Bass() {

    }

    public BassStep getBassStep() {
        return bassStep;
    }

    public void setBassStep(BassStep bassStep) {
        this.bassStep = bassStep;
    }

    public BassAlter getBassAlter() {
        return bassAlter;
    }

    public void setBassAlter(BassAlter bassAlter) {
        this.bassAlter = bassAlter;
    }
}
