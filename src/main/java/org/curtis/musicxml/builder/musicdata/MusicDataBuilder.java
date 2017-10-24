package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.builder.AbstractBuilder;
import org.curtis.musicxml.score.MusicData;

import java.math.BigDecimal;

public class MusicDataBuilder extends AbstractBuilder {
    private MusicData musicData;
    private BigDecimal currentDivisions;

    public MusicDataBuilder(MusicData musicData) {
        this.musicData = musicData;
    }

    public StringBuilder build() {
        if(musicData instanceof Attributes) {
            AttributesBuilder attributesBuilder = new AttributesBuilder((Attributes)musicData);
            return attributesBuilder.build();
        }

        return stringBuilder;
    }

    public BigDecimal getCurrentDivisions() {
        return currentDivisions;
    }

    public void setCurrentDivisions(BigDecimal currentDivisions) {
        this.currentDivisions = currentDivisions;
    }
}
