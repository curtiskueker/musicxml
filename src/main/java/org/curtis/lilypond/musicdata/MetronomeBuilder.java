package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.util.NoteUtil;
import org.curtis.musicxml.direction.directiontype.metronome.BeatMetronome;
import org.curtis.musicxml.direction.directiontype.metronome.BeatUnit;
import org.curtis.musicxml.direction.directiontype.metronome.PerMinute;

public class MetronomeBuilder extends MusicDataBuilder {
    public StringBuilder buildBeatMetronome(BeatMetronome beatMetronome) throws BuildException {
        PerMinute perMinute = beatMetronome.getPerMinute();
        BeatUnit beatUnit1 = beatMetronome.getBeatUnit1();

        // only per-minute implemented
        if(perMinute == null) return stringBuilder;
        if(beatUnit1 == null) return stringBuilder;

        appendLine("");
        append("\\tempo ");
        append(NoteUtil.getNoteTypeValue(beatUnit1.getBeatUnit()));
        append(" = ");
        append(perMinute.getPerMinute());

        return stringBuilder;
    }
}
