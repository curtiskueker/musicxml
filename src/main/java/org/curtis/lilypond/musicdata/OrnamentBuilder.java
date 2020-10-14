package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.util.PlacementBuildUtil;
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
        append(PlacementBuildUtil.getPlacement(trillMark.getDisplay()));
        append("\\trill");

        return stringBuilder;
    }

    public StringBuilder buildTurn(Turn turn) {
        append(PlacementBuildUtil.getPlacement(turn.getDisplay()));
        append("\\turn");

        return stringBuilder;
    }

    public StringBuilder buildInvertedTurn(InvertedTurn invertedTurn) {
        append(PlacementBuildUtil.getPlacement(invertedTurn.getDisplay()));
        append("\\reverseturn");

        return stringBuilder;
    }

    public StringBuilder buildMordent(Mordent mordent) {
        append(PlacementBuildUtil.getPlacement(mordent.getDisplay()));
        append("\\mordent");

        return stringBuilder;
    }

    public StringBuilder buildWavyLine(WavyLine wavyLine) {
        switch (wavyLine.getType()) {
            case START:
                append("\\startTrillSpan");
                break;
            case STOP:
                append("\\stopTrillSpan");
                break;
        }

        return stringBuilder;
    }

    public StringBuilder buildInvertedMordent(InvertedMordent invertedMordent) {
        append(PlacementBuildUtil.getPlacement(invertedMordent.getDisplay()));
        append("\\prall");

        return stringBuilder;
    }

    public StringBuilder buildTremolo(Tremolo tremolo) {
            switch (tremolo.getType()) {
                case SINGLE:
                    Integer tremoloMarks = tremolo.getTremoloMarks();
                    if(tremoloMarks != null) {
                        append(":");
                        append(MathUtil.truncate(MathUtil.multiply(MathUtil.exp(MathUtil.newBigDecimal(2), tremoloMarks), MathUtil.newBigDecimal(4))));
                    }
                    break;
                case UNMEASURED:
                    append(":32");
                    break;
        }

        return stringBuilder;
    }
}
