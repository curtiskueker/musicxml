package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.direction.Sound;

public class SoundBuilder extends BaseBuilder {
    private Sound sound;

    public SoundBuilder(Sound sound) {
        this.sound = sound;
    }

    public StringBuilder build() {
        if (sound == null) return stringBuilder;

        append("<sound");
        buildAttribute("dacapo", BuilderUtil.yesOrNo(sound.getDacapo()));
        buildAttribute("segno", sound.getSegno());
        buildAttribute("dalsegno", sound.getDalsegno());
        buildAttribute("coda", sound.getCoda());
        buildAttribute("tocoda", sound.getTocoda());
        buildAttribute("forward-repeat", BuilderUtil.yesOrNo(sound.getForwardRepeat()));
        buildAttribute("pizzicato", BuilderUtil.yesOrNo(sound.getPizzicato()));
        buildAttribute("fine", sound.getFine());
        appendLine(">");
        DirectionBuilder directionBuilder = new DirectionBuilder();
        append(directionBuilder.buildOffset(sound.getOffset()).toString());
        appendLine("</sound>");

        return stringBuilder;
    }
}
