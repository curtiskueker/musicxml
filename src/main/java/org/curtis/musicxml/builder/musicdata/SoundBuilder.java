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

        buildOpenElement("sound");
        buildAttribute("tempo", sound.getTempo());
        buildAttribute("dynamics", sound.getDynamics());
        buildAttribute("dacapo",  sound.getDacapo());
        buildAttribute("segno", sound.getSegno());
        buildAttribute("dalsegno", sound.getDalsegno());
        buildAttribute("coda", sound.getCoda());
        buildAttribute("tocoda", sound.getTocoda());
        buildAttribute("divisions", sound.getDivisions());
        buildAttribute("forward-repeat",  sound.getForwardRepeat());
        buildAttribute("fine", sound.getFine());
        buildAttribute("time-only", sound.getTimeOnly());
        buildAttribute("pizzicato",  sound.getPizzicato());
        buildAttribute("pan", sound.getPan());
        buildAttribute("elevation", sound.getElevation());
        buildAttribute("damper-pedal", sound.getDamperPedal());
        buildAttribute("soft-pedal", sound.getSoftPedal());
        buildAttribute("sostenuto-pedal", sound.getSostenutoPedal());
        buildCloseElement();
        append(DirectionBuilder.buildOffset(sound.getOffset()));
        buildEndElement("sound");

        return stringBuilder;
    }
}
