package org.curtis.musicxml.note.notation.ornament;

public class Tremolo extends Ornament {
    private Integer tremoloMarks;
    private String type = "single";
    // TODO: print style
    // TODO: placement

    public Tremolo() {

    }

    public Integer getTremoloMarks() {
        return tremoloMarks;
    }

    public void setTremoloMarks(Integer tremoloMarks) {
        this.tremoloMarks = tremoloMarks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
