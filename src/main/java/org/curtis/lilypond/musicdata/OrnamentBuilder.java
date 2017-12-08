package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.notation.ornament.Tremolo;
import org.curtis.musicxml.note.notation.ornament.TrillMark;
import org.curtis.util.MathUtil;

public class OrnamentBuilder extends MusicDataBuilder {
    public OrnamentBuilder() {

    }

    public StringBuilder buildTrillMark(TrillMark trillMark) {
        append(PlacementBuildUtil.getPlacement(trillMark.getPlacement()));
        append("\\trill");

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
