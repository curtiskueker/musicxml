package org.curtis.musicxml.builder;

import org.curtis.musicxml.builder.musicdata.MusicDataBuilder;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;

import java.math.BigDecimal;
import java.util.List;

public class MeasureBuilder extends AbstractBuilder {
    private Measure measure;
    private BigDecimal currentDivisions;

    public MeasureBuilder(Measure measure) {
        this.measure = measure;
    }

    public StringBuilder build() {
        List<MusicData> musicDataList = measure.getMusicDataList();

        for (MusicData musicData : musicDataList) {
            MusicDataBuilder musicDataBuilder = new MusicDataBuilder(musicData);
            musicDataBuilder.setCurrentDivisions(currentDivisions);
            append(musicDataBuilder.build().toString());
            currentDivisions = musicDataBuilder.getCurrentDivisions();
        }

        appendLine("c'4 e'4 g'4");
        appendLine("\\bar \"|\"");

        return stringBuilder;
    }

    public BigDecimal getCurrentDivisions() {
        return currentDivisions;
    }

    public void setCurrentDivisions(BigDecimal currentDivisions) {
        this.currentDivisions = currentDivisions;
    }
}
