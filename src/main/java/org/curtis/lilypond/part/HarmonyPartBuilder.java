package org.curtis.lilypond.part;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.exception.TimeSignatureException;
import org.curtis.lilypond.musicdata.MusicDataBuilder;
import org.curtis.lilypond.util.TimeSignatureUtil;
import org.curtis.musicxml.direction.harmony.Harmony;
import org.curtis.musicxml.direction.harmony.Root;
import org.curtis.musicxml.direction.harmony.RootStep;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MeasureItem;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Part;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HarmonyPartBuilder extends FilteredPartBuilder {
    private Part part;

    public HarmonyPartBuilder(Part part) {
        this.part = part;
    }

    public StringBuilder build() throws BuildException {
        PartBuilder.CURRENT_PART_ID = "Harmony Builder";
        appendLine("\\new ChordNames \\chordmode {");

        List<MusicDataBuilder> musicDataBuilders = new ArrayList<>();
        Harmony currentHarmony = newEmptyHarmony();

        for(Measure measure : part.getMeasures()) {
            for(MeasureItem measureItem : measure.getMeasureItems()) {
                MusicData musicData;
                try {
                    musicData = MusicXmlUtil.getMusicDataForMeasureItem(measureItem);
                } catch (MusicXmlException e) {
                    throw new BuildException(e);
                }
                adjustCurrentDuration(musicData);

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
                }
            }
        }

        // set the last harmony to the accumulated duration
        currentHarmony.setTotalBeats(MathUtil.divide(currentDuration, currentDivisions));
        currentHarmony.setTotalBeats(MathUtil.divide(currentDuration, currentDivisions));

        for (MusicDataBuilder musicDataBuilder : musicDataBuilders) {
            append(musicDataBuilder.build().toString());
        }

        appendLine();
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
