package org.curtis.musicxml.direction.harmony;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("root")
public class Root extends HarmonyChord {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "root_step_id")
    private RootStep rootStep;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "root_alter_id")
    private RootAlter rootAlter;

    public Root() {

    }

    public RootStep getRootStep() {
        return rootStep;
    }

    public void setRootStep(RootStep rootStep) {
        this.rootStep = rootStep;
    }

    public RootAlter getRootAlter() {
        return rootAlter;
    }

    public void setRootAlter(RootAlter rootAlter) {
        this.rootAlter = rootAlter;
    }
}
