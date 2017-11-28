package org.curtis.musicxml.note;

public class Unpitched extends FullNoteType {
    private Step displayStep;
    private Integer displayOctave;

    public Unpitched() {

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
}
