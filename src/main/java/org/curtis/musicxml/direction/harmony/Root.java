package org.curtis.musicxml.direction.harmony;

public class Root {
    private RootStep rootStep;
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
