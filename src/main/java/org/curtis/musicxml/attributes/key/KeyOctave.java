package org.curtis.musicxml.attributes.key;

public class KeyOctave {
    private Integer octave;
    private Integer number;
    private Boolean cancel;

    public KeyOctave() {

    }

    public Integer getOctave() {
        return octave;
    }

    public void setOctave(Integer octave) {
        this.octave = octave;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }
}
