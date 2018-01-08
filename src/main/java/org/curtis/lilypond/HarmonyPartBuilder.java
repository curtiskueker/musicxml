package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.exception.TimeSignatureException;
import org.curtis.lilypond.musicdata.MusicDataBuilder;
import org.curtis.lilypond.util.AttributesUtil;
import org.curtis.lilypond.util.TimeSignatureUtil;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.direction.harmony.Harmony;
import org.curtis.musicxml.direction.harmony.Root;
import org.curtis.musicxml.direction.harmony.RootStep;
import org.curtis.musicxml.note.Backup;
import org.curtis.musicxml.note.Forward;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Part;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HarmonyPartBuilder extends AbstractBuilder {
    private Part part;

    public HarmonyPartBuilder(Part part) {
        this.part = part;
    }

    public StringBuilder build() throws BuildException {
        PartBuilder.CURRENT_PART_ID = "Harmony Builder";
        appendLine("\\new ChordNames \\chordmode {");

        BigDecimal currentDivisions = MathUtil.ZERO;
        BigDecimal currentDuration = MathUtil.ZERO;

        List<MusicDataBuilder> musicDataBuilders = new ArrayList<>();
        Harmony currentHarmony = newEmptyHarmony();

        for(Measure measure : part.getMeasures()) {
            for(MusicData musicData : measure.getMusicDataList()) {
                if(musicData instanceof Harmony) {
                    BigDecimal totalBeats = MathUtil.divide(currentDuration, currentDivisions);

                    if (musicDataBuilders.isEmpty() && MathUtil.isPositive(currentDuration)) {
                        MusicDataBuilder emptyHarmonyBuilder = new MusicDataBuilder(currentHarmony);
                        musicDataBuilders.add(emptyHarmonyBuilder);
                    }

                    BigDecimal currentMeasureBeats;
                    try {
                        currentMeasureBeats = TimeSignatureUtil.getCurrentMeasureBeats();
                    } catch (TimeSignatureException e) {
                        throw new BuildException(e.getMessage());
                    }
                    if (MathUtil.largerThan(totalBeats, currentMeasureBeats)) {
                        currentHarmony.setTotalBeats(currentMeasureBeats);
                        totalBeats = MathUtil.subtract(totalBeats, currentMeasureBeats);
                        while (MathUtil.isPositive(totalBeats)) {
                            Harmony emptyHarmony = newEmptyHarmony();
                            BigDecimal emptyHarmonyTotalBeats = MathUtil.largerThan(totalBeats, currentMeasureBeats) ? currentMeasureBeats : totalBeats;
                            emptyHarmony.setTotalBeats(emptyHarmonyTotalBeats);
                            MusicDataBuilder emptyHarmonyBuilder = new MusicDataBuilder(emptyHarmony);
                            musicDataBuilders.add(emptyHarmonyBuilder);
                            totalBeats = MathUtil.subtract(totalBeats, emptyHarmonyTotalBeats);
                        }
                    } else {
                        currentHarmony.setTotalBeats(totalBeats);
                    }

                    Harmony harmony = (Harmony)musicData;
                    MusicDataBuilder musicDataBuilder = new MusicDataBuilder(harmony);
                    musicDataBuilders.add(musicDataBuilder);
                    currentHarmony = harmony;
                    currentDuration = MathUtil.ZERO;
                } else if (musicData instanceof Attributes) {
                    Attributes attributes = (Attributes)musicData;
                    AttributesUtil.setCurrentAttributes(attributes);
                    BigDecimal divisions = attributes.getDivisions();
                    if (divisions != null) currentDivisions = divisions;
                } else if (musicData instanceof Note) {
                    Note note = (Note)musicData;
                    if (!note.getFullNote().isChord()) currentDuration = MathUtil.add(currentDuration, note.getDuration());
                } else if (musicData instanceof Backup) {
                    Backup backup = (Backup)musicData;
                    currentDuration = MathUtil.subtract(currentDuration, backup.getDuration());
                } else if (musicData instanceof Forward) {
                    Forward forward = (Forward)musicData;
                    currentDuration = MathUtil.add(currentDuration, forward.getDuration());
                }
            }
        }

        // set the last harmony to the accumulated duration
        currentHarmony.setTotalBeats(MathUtil.divide(currentDuration, currentDivisions));

        for (MusicDataBuilder musicDataBuilder : musicDataBuilders) {
            append(musicDataBuilder.build().toString());
        }

        appendLine("");
        appendLine("}");

        return stringBuilder;
    }

    private Harmony newEmptyHarmony() {
        Harmony harmony = new Harmony();
        Root root = new Root();
        RootStep rootStep = new RootStep();
        root.setRootStep(rootStep);
        harmony.getHarmonyChords().add(root);

        return harmony;
    }
}
