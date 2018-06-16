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
        buildAttribute("tempo", BuilderUtil.stringValue(sound.getTempo()));
        buildAttribute("dynamics", BuilderUtil.stringValue(sound.getDynamics()));
        buildAttribute("dacapo", BuilderUtil.yesOrNo(sound.getDacapo()));
        buildAttribute("segno", sound.getSegno());
        buildAttribute("dalsegno", sound.getDalsegno());
        buildAttribute("coda", sound.getCoda());
        buildAttribute("tocoda", sound.getTocoda());
        buildAttribute("divisions", BuilderUtil.stringValue(sound.getDivisions()));
        buildAttribute("forward-repeat", BuilderUtil.yesOrNo(sound.getForwardRepeat()));
        buildAttribute("fine", sound.getFine());
        buildAttribute("time-only", sound.getTimeOnly());
        buildAttribute("pizzicato", BuilderUtil.yesOrNo(sound.getPizzicato()));
        buildAttribute("pan", BuilderUtil.stringValue(sound.getPan()));
        buildAttribute("elevation", BuilderUtil.stringValue(sound.getElevation()));
        buildAttribute("damper-pedal", sound.getDamperPedal());
        buildAttribute("soft-pedal", sound.getSoftPedal());
        buildAttribute("sostenuto-pedal", sound.getSostenutoPedal());
        appendLine(">");
        DirectionBuilder directionBuilder = new DirectionBuilder();
        append(directionBuilder.buildOffset(sound.getOffset()).toString());
        appendLine("</sound>");

        return stringBuilder;
    }
}
