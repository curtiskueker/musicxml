package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.notation.ornament.InvertedMordent;
import org.curtis.musicxml.note.notation.ornament.InvertedTurn;
import org.curtis.musicxml.note.notation.ornament.Mordent;
import org.curtis.musicxml.note.notation.ornament.Tremolo;
import org.curtis.musicxml.note.notation.ornament.TrillMark;
import org.curtis.musicxml.note.notation.ornament.Turn;
import org.curtis.musicxml.note.notation.ornament.WavyLine;
import org.curtis.util.MathUtil;

public class OrnamentBuilder extends MusicDataBuilder {
    public OrnamentBuilder() {

    }

    public StringBuilder buildTrillMark(TrillMark trillMark) {
        append(PlacementBuildUtil.getPlacement(trillMark.getPlacement()));
        append("\\trill");

        return stringBuilder;
    }

    public StringBuilder buildTurn(Turn turn) {
        append(PlacementBuildUtil.getPlacement(turn.getPlacement()));
        append("\\turn");

        return stringBuilder;
    }

    public StringBuilder buildInvertedTurn(InvertedTurn invertedTurn) {
        append(PlacementBuildUtil.getPlacement(invertedTurn.getPlacement()));
        append("\\reverseturn");

        return stringBuilder;
    }

    public StringBuilder buildMordent(Mordent mordent) {
        append(PlacementBuildUtil.getPlacement(mordent.getPlacement()));
        append("\\mordent");

        return stringBuilder;
    }

    // TODO: wavy line implementation
    public StringBuilder buildWavyLine(WavyLine wavyLine) {
        System.err.println("Warning: OrnamentBuilder.buildWavyLine not implemented");
        return stringBuilder;
    }

    public StringBuilder buildInvertedMordent(InvertedMordent invertedMordent) {
        append(PlacementBuildUtil.getPlacement(invertedMordent.getPlacement()));
        append("\\prall");

        return stringBuilder;
    }

    public StringBuilder buildTremolo(Tremolo tremolo) {
        Integer tremoloMarks = tremolo.getTremoloMarks();
        if(tremoloMarks != null) {
            Connection tremoloType = tremolo.getType();
            switch (tremoloType) {
                case SINGLE:
                    append(":");
                    Integer tremoloValue = MathUtil.multiply(MathUtil.exp(MathUtil.newBigDecimal(2), tremoloMarks), MathUtil.newBigDecimal(4)).intValue();
                    append(String.valueOf(tremoloValue));
                    break;
            }
        }

        return stringBuilder;
    }
}
