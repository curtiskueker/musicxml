package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.AbstractBuilder;
import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.Tied;
import org.curtis.musicxml.note.notation.articulation.Accent;
import org.curtis.musicxml.note.notation.articulation.DetachedLegato;
import org.curtis.musicxml.note.notation.articulation.Staccato;
import org.curtis.musicxml.note.notation.articulation.Tenuto;
import org.curtis.musicxml.note.notation.ornament.Tremolo;
import org.curtis.musicxml.note.notation.ornament.TrillMark;
import org.curtis.util.MathUtil;

public class NotationBuilder extends AbstractBuilder {
    private Notation notation;

    public NotationBuilder(Notation notation) {
        this.notation = notation;
    }

    public StringBuilder build() {
        if(notation instanceof Tied) {
            Tied tied = (Tied)notation;
            Connection tieType = tied.getType();
            switch (tieType) {
                case START:
                case CONTINUE:
                    append(PlacementBuildUtil.getPlacement(tied.getPlacement()));
                    append("~");
                    break;
            }
        } else if (notation instanceof Slur) {
            Slur slur = (Slur)notation;
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
        } else if(notation instanceof Fermata) {
            append("\\fermata");
        } else if (notation instanceof Accent) {
            Accent accent = (Accent)notation;
            Placement accentPlacement = accent.getPlacement();
            if (accentPlacement != null) {
                append(PlacementBuildUtil.getPlacement(accentPlacement.getPlacement()));
            }
            append("\\accent");
        } else if(notation instanceof Staccato) {
            Staccato staccato = (Staccato)notation;
            Placement staccatoPlacement = staccato.getPlacement();
            if (staccatoPlacement != null) {
                append(PlacementBuildUtil.getPlacement(staccatoPlacement.getPlacement()));
            }
            append("\\staccato");
        } else if(notation instanceof Tenuto) {
            Tenuto tenuto = (Tenuto)notation;
            Placement tenutoPlacement = tenuto.getPlacement();
            if (tenutoPlacement != null) {
                append(PlacementBuildUtil.getPlacement(tenutoPlacement.getPlacement()));
            }
            append("\\tenuto");
        } else if(notation instanceof DetachedLegato) {
            DetachedLegato detachedLegato = (DetachedLegato) notation;
            Placement detachedLegatoPlacement = detachedLegato.getPlacement();
            if (detachedLegatoPlacement != null) {
                append(PlacementBuildUtil.getPlacement(detachedLegatoPlacement.getPlacement()));
            }
            append("\\portato");
        } else if(notation instanceof TrillMark) {
            TrillMark trillMark = (TrillMark)notation;
            append(PlacementBuildUtil.getPlacement(trillMark.getPlacement()));
            append("\\trill");
        } else if(notation instanceof Tremolo) {
            Tremolo tremolo = (Tremolo)notation;
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
        }

        return stringBuilder;
    }
}
