package org.curtis.musicxml.note;

public class Rest extends FullNote {
    private Step displayStep;
    private Integer displayOctave;
    private Boolean measure = false;

    public Rest() {

    }

    public Step getDisplayStep() {
        return displayStep;
    }

    public void setDisplayStep(Step displayStep) {
        this.displayStep = displayStep;
    }

    public Integer getDisplayOctave() {
        return displayOctave;
    }

    public void setDisplayOctave(Integer displayOctave) {
        this.displayOctave = displayOctave;
    }

    public Boolean getMeasure() {
        return measure;
    }

    public void setMeasure(Boolean measure) {
        this.measure = measure;
    }
}
