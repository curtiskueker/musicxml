package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.notation.articulation.Accent;
import org.curtis.musicxml.note.notation.articulation.DetachedLegato;
import org.curtis.musicxml.note.notation.articulation.Staccato;
import org.curtis.musicxml.note.notation.articulation.Tenuto;

public class NoteNotationArticulationBuilder extends MusicDataBuilder {
    public NoteNotationArticulationBuilder() {

    }

    public StringBuilder buildAccent(Accent accent) {
        Placement accentPlacement = accent.getPlacement();
        if (accentPlacement != null) {
            append(PlacementBuildUtil.getPlacement(accentPlacement.getPlacement()));
        }
        append("\\accent");

        return stringBuilder;
    }

    public StringBuilder buildStaccato(Staccato staccato) {
        Placement staccatoPlacement = staccato.getPlacement();
        if (staccatoPlacement != null) {
            append(PlacementBuildUtil.getPlacement(staccatoPlacement.getPlacement()));
        }
        append("\\staccato");

        return stringBuilder;
    }

    public StringBuilder buildTenuto(Tenuto tenuto) {
        Placement tenutoPlacement = tenuto.getPlacement();
        if (tenutoPlacement != null) {
            append(PlacementBuildUtil.getPlacement(tenutoPlacement.getPlacement()));
        }
        append("\\tenuto");

        return stringBuilder;
    }

    public StringBuilder buildDetachedLegato(DetachedLegato detachedLegato) {
        Placement detachedLegatoPlacement = detachedLegato.getPlacement();
        if (detachedLegatoPlacement != null) {
            append(PlacementBuildUtil.getPlacement(detachedLegatoPlacement.getPlacement()));
        }
        append("\\portato");

        return stringBuilder;
    }
}
