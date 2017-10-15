package org.curtis.musicxml.note;

public class Rest {
    private String displayStep;
    private Integer displayOctave;
    private Boolean measure;

    public Rest() {

    }

    public String getDisplayStep() {
        return displayStep;
    }

    public void setDisplayStep(String displayStep) {
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
