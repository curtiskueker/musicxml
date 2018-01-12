package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.notation.articulation.Accent;
import org.curtis.musicxml.note.notation.articulation.DetachedLegato;
import org.curtis.musicxml.note.notation.articulation.Staccato;
import org.curtis.musicxml.note.notation.articulation.StrongAccent;
import org.curtis.musicxml.note.notation.articulation.Tenuto;

public class ArticulationBuilder extends MusicDataBuilder {
    public ArticulationBuilder() {

    }

    public StringBuilder buildAccent(Accent accent) {
        Placement placement = accent.getPlacement();
        if (placement != null) append(PlacementBuildUtil.getPlacement(placement.getPlacement()));
        append("\\accent");

        return stringBuilder;
    }

    public StringBuilder buildStrongAccent(StrongAccent strongAccent) {
        Placement placement = strongAccent.getPlacement();
        if (placement != null) append(PlacementBuildUtil.getPlacement(placement.getPlacement()));
        append("\\marcato");

        return stringBuilder;
    }
    public StringBuilder buildStaccato(Staccato staccato) {
        Placement placement = staccato.getPlacement();
        if (placement != null) append(PlacementBuildUtil.getPlacement(placement.getPlacement()));
        append("\\staccato");

        return stringBuilder;
    }

    public StringBuilder buildTenuto(Tenuto tenuto) {
        Placement placement = tenuto.getPlacement();
        if (placement != null) append(PlacementBuildUtil.getPlacement(placement.getPlacement()));
        append("\\tenuto");

        return stringBuilder;
    }

    public StringBuilder buildDetachedLegato(DetachedLegato detachedLegato) {
        Placement placement = detachedLegato.getPlacement();
        if (placement != null) append(PlacementBuildUtil.getPlacement(placement.getPlacement()));
        append("\\portato");

        return stringBuilder;
    }
}
