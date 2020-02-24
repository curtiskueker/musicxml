package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.direction.Sound;
import org.curtis.musicxml.direction.SoundMidi;

public class SoundBuilder extends MusicDataBuilder {
    private Sound sound;

    public SoundBuilder(Sound sound) {
        this.sound = sound;
    }

    public StringBuilder build() {
        if (sound == null) return stringBuilder;

        buildOpenElement("sound");
        buildAttribute("id", sound.getElementId());
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
        for (SoundMidi soundMidi : sound.getSoundMidis()) {
            buildMidiDevice(soundMidi.getMidiDevice());
            buildMidiInstrument(soundMidi.getMidiInstrument());
            buildPlay(soundMidi.getPlay());
        }
        append(DirectionBuilder.buildOffset(sound.getOffset()));
        buildEndElement("sound");

        return stringBuilder;
    }
}
