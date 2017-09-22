package org.curtis.musicxml.direction.harmony;

import java.util.List;

public class HarmonyChord {
    private Root root;
    // TODO: function
    private Kind kind;
    private Inversion inversion;
    private Bass bass;
    private List<Degree> degrees;

    public HarmonyChord() {

    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Inversion getInversion() {
        return inversion;
    }

    public void setInversion(Inversion inversion) {
        this.inversion = inversion;
    }

    public Bass getBass() {
        return bass;
    }

    public void setBass(Bass bass) {
        this.bass = bass;
    }

    public List<Degree> getDegrees() {
        return degrees;
    }

    public void setDegrees(List<Degree> degrees) {
        this.degrees = degrees;
    }
}
