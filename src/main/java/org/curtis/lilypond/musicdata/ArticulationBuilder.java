package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.musicxml.display.Display;
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
        buildPlacement(accent.getDisplay());
        append("\\accent");

        return stringBuilder;
    }

    public StringBuilder buildStrongAccent(StrongAccent strongAccent) {
        buildPlacement(strongAccent.getDisplay());
        append("\\marcato");

        return stringBuilder;
    }
    public StringBuilder buildStaccato(Staccato staccato) {
        buildPlacement(staccato.getDisplay());
        append("\\staccato");

        return stringBuilder;
    }

    public StringBuilder buildTenuto(Tenuto tenuto) {
        buildPlacement(tenuto.getDisplay());
        append("\\tenuto");

        return stringBuilder;
    }

    public StringBuilder buildDetachedLegato(DetachedLegato detachedLegato) {
        buildPlacement(detachedLegato.getDisplay());
        append("\\portato");

        return stringBuilder;
    }

    public StringBuilder buildStaccatissimo(Staccatissimo staccatissimo) {
        buildPlacement(staccatissimo.getDisplay());
        append("\\staccatissimo");

        return stringBuilder;
    }

    public StringBuilder buildBreathMark(BreathMark breathMark) {
        append("\\breathe");

        return stringBuilder;
    }

    private void buildPlacement(Display display) {
        append(PlacementBuildUtil.getPlacement(display));
    }
}
