package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.exception.DurationException;
import org.curtis.lilypond.util.NoteUtil;
import org.curtis.lilypond.util.TimeSignatureUtil;
import org.curtis.musicxml.direction.harmony.Harmony;
import org.curtis.musicxml.direction.harmony.HarmonyChord;
import org.curtis.musicxml.direction.harmony.Kind;
import org.curtis.musicxml.direction.harmony.KindValue;
import org.curtis.musicxml.direction.harmony.Root;
import org.curtis.musicxml.direction.harmony.RootAlter;

import java.util.List;

public class HarmonyBuilder extends MusicDataBuilder {
    public HarmonyBuilder() {

    }

    public StringBuilder buildHarmony(Harmony harmony) throws BuildException {
        List<HarmonyChord> harmonyChords = harmony.getHarmonyChords();
        for(HarmonyChord harmonyChord : harmonyChords) {
            if(harmonyChord instanceof Root) {
                Root root = (Root)harmonyChord;
                append(NoteUtil.getStep(root.getRootStep().getValue()));

                RootAlter rootAlter = root.getRootAlter();
                if(rootAlter != null) {
                    append(NoteUtil.getAlter(rootAlter.getValue()));
                }
            }

            try {
                append(TimeSignatureUtil.getRepresentationValue(harmony.getTotalBeats()));
            } catch (DurationException e) {
                throw new BuildException(e.getMessage());
            }

            Kind kind = harmonyChord.getKind();
            if (kind != null) {
                KindValue kindValue = kind.getValue();
                switch (kindValue) {
                    case MINOR:
                        append(":m");
                        break;
                    case DOMINANT:
                        append(":7");
                        break;
                }
            }

            append(" ");
        }

        return stringBuilder;
    }
}
