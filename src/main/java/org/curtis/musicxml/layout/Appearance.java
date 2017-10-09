package org.curtis.musicxml.layout;

import java.util.List;

public class Appearance {
    private List<LineWidth> lineWidths;
    private List<NoteSize> noteSizes;
    private List<Distance> distances;
    private List<OtherAppearance> otherAppearances;

    public Appearance() {

    }

    public List<LineWidth> getLineWidths() {
        return lineWidths;
    }

    public void setLineWidths(List<LineWidth> lineWidths) {
        this.lineWidths = lineWidths;
    }

    public List<NoteSize> getNoteSizes() {
        return noteSizes;
    }

    public void setNoteSizes(List<NoteSize> noteSizes) {
        this.noteSizes = noteSizes;
    }

    public List<Distance> getDistances() {
        return distances;
    }

    public void setDistances(List<Distance> distances) {
        this.distances = distances;
    }

    public List<OtherAppearance> getOtherAppearances() {
        return otherAppearances;
    }

    public void setOtherAppearances(List<OtherAppearance> otherAppearances) {
        this.otherAppearances = otherAppearances;
    }
}
