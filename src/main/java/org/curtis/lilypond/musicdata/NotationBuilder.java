package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.lilypond.util.TypeUtil;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.AccidentalType;
import org.curtis.musicxml.note.notation.AccidentalMark;
import org.curtis.musicxml.note.notation.Arpeggiate;
import org.curtis.musicxml.note.notation.Articulations;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.Ornaments;
import org.curtis.musicxml.note.notation.Slide;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.SlurType;
import org.curtis.musicxml.note.notation.Technicals;
import org.curtis.musicxml.note.notation.Tied;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.note.notation.articulation.Articulation;
import org.curtis.musicxml.note.notation.ornament.Ornament;
import org.curtis.musicxml.note.notation.technical.Technical;

public class NotationBuilder extends MusicDataBuilder {
    public StringBuilder buildTied(Tied tied) throws BuildException {
        if (tied.isRepeatTie()) {
                append("\\repeatTie ");
                return stringBuilder;
        }

        switch (tied.getTiedType()) {
            case START:
            case CONTINUE:
                if (tied.isUnterminated()) throw new BuildException("Unterminated tie");
                append(PlacementBuildUtil.getPlacement(tied.getDisplay()));
                append("~");
                break;
        }

        return stringBuilder;
    }

    public StringBuilder buildSlur(Slur slur) {
        Connection connectionType = slur.getConnectionType();
        SlurType slurType = slur.getSlurType();
        if (connectionType == Connection.START) append(PlacementBuildUtil.getPlacement(slur.getDisplay()));
        if (slurType == SlurType.PHRASING) append("\\");
        switch (connectionType) {
            case START:
                append("(");
                break;
            case STOP:
                append(")");
                break;
        }

        return stringBuilder;
    }

    // Built in NoteBuilder.buildTupletNotes()
    public StringBuilder buildTuplet(Tuplet tuplet) {
        return stringBuilder;
    }

    public StringBuilder buildSlide(Slide slide) {
        // two-note glissando/slide only
        if (slide.getType() != Connection.START) return stringBuilder;

        append("\\glissando ");

        return stringBuilder;
    }

    public StringBuilder buildOrnaments(Ornaments ornaments) throws BuildException {
        if (!TypeUtil.getBooleanDefaultYes(ornaments.isPrintObject())) return stringBuilder;

        for (Ornament ornament : ornaments.getOrnaments()) {
            MusicDataBuilder musicDataBuilder = new MusicDataBuilder(ornament);
            append(musicDataBuilder.build().toString());
        }

        return stringBuilder;
    }

    public StringBuilder buildTechnicals(Technicals technicals) throws BuildException {
        for (Technical technical : technicals.getTechnicals()) {
            MusicDataBuilder musicDataBuilder = new MusicDataBuilder(technical);
            append(musicDataBuilder.build().toString());
        }

        return stringBuilder;
    }

    public StringBuilder buildArticulations(Articulations articulations) throws BuildException {
        for (Articulation articulation : articulations.getArticulationList()) {
            MusicDataBuilder musicDataBuilder = new MusicDataBuilder(articulation);
            append(musicDataBuilder.build().toString());
        }

        return stringBuilder;
    }

    public StringBuilder buildFermata(Fermata fermata) {
        append("\\fermata");
        if (fermata.isMarkup()) append("Markup");

        return stringBuilder;
    }

    public StringBuilder buildArpeggiate(Arpeggiate arpeggiate) {
        append("\\arpeggio");

        return stringBuilder;
    }

    public StringBuilder buildAccidentalMark(AccidentalMark accidentalMark) {
        AccidentalType accidentalType = accidentalMark.getAccidentalType();
        if (accidentalType == null) return stringBuilder;

        String accidentalMarkup = null;
        switch (accidentalType) {
            case SHARP:
                accidentalMarkup = "sharp";
                break;
            case NATURAL:
                accidentalMarkup = "natural";
                break;
            case FLAT:
                accidentalMarkup = "flat";
                break;
        }
        if (accidentalMarkup == null) return stringBuilder;

        append(PlacementBuildUtil.getPlacementDefaultAbove(accidentalMark.getDisplay()));
        append("\\markup ");
        append("\\");
        append(accidentalMarkup);
        append(" ");

        return stringBuilder;
    }
}
