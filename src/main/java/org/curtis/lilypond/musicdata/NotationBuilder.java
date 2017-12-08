package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.Tied;

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
        Connection slurType = slur.getType();
        switch (slurType) {
            case START:
                append(PlacementBuildUtil.getPlacement(slur.getPlacement()));
                append("(");
                break;
            case STOP:
                append(")");
                break;
        }

        return stringBuilder;
    }

    public StringBuilder buildFermata(Fermata fermata) {
        append("\\fermata");

        return stringBuilder;
    }
}
