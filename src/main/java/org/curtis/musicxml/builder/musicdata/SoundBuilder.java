package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.direction.Sound;

public class SoundBuilder extends BaseBuilder {
    private Sound sound;

    public SoundBuilder(Sound sound) {
        this.sound = sound;
    }

    public StringBuilder build() {
        if (sound == null) return stringBuilder;

        appendLine("<sound>");
        DirectionBuilder directionBuilder = new DirectionBuilder();
        append(directionBuilder.buildOffset(sound.getOffset()).toString());
        buildElementWithValue("segno", sound.getSegno());
        buildElementWithValue("dalsegno", sound.getDalsegno());
        buildElementWithValue("coda", sound.getCoda());
        buildElementWithValue("tocoda", sound.getTocoda());
        buildElementWithValue("fine", sound.getFine());
        appendLine("</sound>");

        return stringBuilder;
    }
}
