package org.curtis.lilypond.part;

import org.curtis.lilypond.MeasureBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class MeasureBlock {
    private List<MeasureBuilder> measureBuilders = new ArrayList<>();
    private SortedSet<String> voices = new TreeSet<>();

    public MeasureBlock() {

    }

    public List<MeasureBuilder> getMeasureBuilders() {
        return measureBuilders;
    }

    public void setMeasureBuilders(List<MeasureBuilder> measureBuilders) {
        this.measureBuilders = measureBuilders;
    }

    public SortedSet<String> getVoices() {
        return voices;
    }

    public void setVoices(SortedSet<String> voices) {
        this.voices = voices;
    }
}
