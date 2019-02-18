package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.note.PrintPlacement;
import org.curtis.musicxml.note.notation.articulation.Accent;
import org.curtis.musicxml.note.notation.articulation.BreathMark;
import org.curtis.musicxml.note.notation.articulation.DetachedLegato;
import org.curtis.musicxml.note.notation.articulation.Staccatissimo;
import org.curtis.musicxml.note.notation.articulation.Staccato;
import org.curtis.musicxml.note.notation.articulation.StrongAccent;
import org.curtis.musicxml.note.notation.articulation.Tenuto;

public class ArticulationBuilder extends MusicDataBuilder {
    public ArticulationBuilder() {

    }

    public StringBuilder buildAccent(Accent accent) {
        buildPlacement(accent.getPrintPlacement());
        append("\\accent");

        return stringBuilder;
    }

    public StringBuilder buildStrongAccent(StrongAccent strongAccent) {
        buildPlacement(strongAccent.getPrintPlacement());
        append("\\marcato");

        return stringBuilder;
    }
    public StringBuilder buildStaccato(Staccato staccato) {
        buildPlacement(staccato.getPrintPlacement());
        append("\\staccato");

        return stringBuilder;
    }

    public StringBuilder buildTenuto(Tenuto tenuto) {
        buildPlacement(tenuto.getPrintPlacement());
        append("\\tenuto");

        return stringBuilder;
    }

    public StringBuilder buildDetachedLegato(DetachedLegato detachedLegato) {
        buildPlacement(detachedLegato.getPrintPlacement());
        append("\\portato");

        return stringBuilder;
    }

    public StringBuilder buildStaccatissimo(Staccatissimo staccatissimo) {
        buildPlacement(staccatissimo.getPrintPlacement());
        append("\\staccatissimo");

        return stringBuilder;
    }

    public StringBuilder buildBreathMark(BreathMark breathMark) {
        append("\\breathe");

        return stringBuilder;
    }

    private void buildPlacement(PrintPlacement placement) {
        if (placement == null) return;
        buildPlacement(placement.getPlacement());
    }

    private void buildPlacement(Location placement) {
        append(PlacementBuildUtil.getPlacement(placement));
    }
}
