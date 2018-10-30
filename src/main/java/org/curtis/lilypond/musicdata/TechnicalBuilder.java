package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.util.TypeUtil;
import org.curtis.musicxml.note.notation.technical.Harmonic;
import org.curtis.musicxml.note.notation.technical.HarmonicType;

public class TechnicalBuilder extends MusicDataBuilder {
    public StringBuilder buildHarmonic(Harmonic harmonic) {
        if (!TypeUtil.getBooleanDefaultYes(harmonic.getPrintObject())) return stringBuilder;

        HarmonicType harmonicType = harmonic.getHarmonicType();
        if (harmonicType == null) append("\\flageolet");
        else append("\\harmonic");

        return stringBuilder;
    }
}
