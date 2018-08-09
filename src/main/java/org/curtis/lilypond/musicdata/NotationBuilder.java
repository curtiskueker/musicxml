package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.lilypond.util.TypeUtil;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.notation.Arpeggiate;
import org.curtis.musicxml.note.notation.Articulations;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.Ornaments;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.SlurType;
import org.curtis.musicxml.note.notation.Tied;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.note.notation.articulation.Articulation;
import org.curtis.musicxml.note.notation.ornament.Ornament;

public class NotationBuilder extends MusicDataBuilder {
    public StringBuilder buildTied(Tied tied) {
        Connection tieType = tied.getType();
        switch (tieType) {
            case START:
            case CONTINUE:
                append(PlacementBuildUtil.getPlacement(tied.getPlacement()));
                append("~");
                break;
        }

        return stringBuilder;
    }

    public StringBuilder buildSlur(Slur slur) {
        Connection connectionType = slur.getConnectionType();
        SlurType slurType = slur.getSlurType();
        if (connectionType == Connection.START) append(PlacementBuildUtil.getPlacement(slur.getPlacement()));
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

    public StringBuilder buildOrnaments(Ornaments ornaments) throws BuildException {
        if (!TypeUtil.getBooleanDefaultYes(ornaments.isPrintObject())) return stringBuilder;

        for (Ornament ornament : ornaments.getOrnaments()) {
            MusicDataBuilder musicDataBuilder = new MusicDataBuilder(ornament);
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

        return stringBuilder;
    }

    public StringBuilder buildArpeggiate(Arpeggiate arpeggiate) {
        append("\\arpeggio");

        return stringBuilder;
    }
}
