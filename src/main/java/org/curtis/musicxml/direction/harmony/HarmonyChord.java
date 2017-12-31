package org.curtis.musicxml.direction.harmony;

import java.util.ArrayList;
import java.util.List;

public abstract class HarmonyChord {
    private Kind kind;
    private Inversion inversion;
    private Bass bass;
    private List<Degree> degrees = new ArrayList<>();

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
