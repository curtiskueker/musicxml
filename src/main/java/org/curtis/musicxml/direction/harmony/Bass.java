package org.curtis.musicxml.direction.harmony;

public class Bass {
    private BassStep bassStep;
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
