package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.util.NoteUtil;
import org.curtis.musicxml.direction.directiontype.metronome.BeatMetronome;
import org.curtis.musicxml.direction.directiontype.metronome.BeatUnit;
import org.curtis.musicxml.direction.directiontype.metronome.MetronomeMark;
import org.curtis.musicxml.direction.directiontype.metronome.PerMinute;

public class MetronomeBuilder extends MusicDataBuilder {
    public StringBuilder buildBeatMetronome(BeatMetronome beatMetronome) throws BuildException {
        // only per-minute implemented
        MetronomeMark metronomeMark1 = beatMetronome.getMetronomeMark1();
        MetronomeMark metronomeMark2 = beatMetronome.getMetronomeMark2();
        if (!(metronomeMark2 instanceof PerMinute)) return stringBuilder;

        BeatUnit beatUnit1 = (BeatUnit)metronomeMark1;
        PerMinute perMinute = (PerMinute)metronomeMark2;

        appendLine();
        append("\\tempo ");
        append(NoteUtil.getNoteTypeValue(beatUnit1.getValue()));
        append(" = ");
        append(perMinute.getValue());
        appendLine();

        return stringBuilder;
    }
}
