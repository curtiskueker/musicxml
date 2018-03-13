package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.direction.harmony.Barre;
import org.curtis.musicxml.direction.harmony.Bass;
import org.curtis.musicxml.direction.harmony.BassAlter;
import org.curtis.musicxml.direction.harmony.BassStep;
import org.curtis.musicxml.direction.harmony.Degree;
import org.curtis.musicxml.direction.harmony.DegreeType;
import org.curtis.musicxml.direction.harmony.DegreeValue;
import org.curtis.musicxml.direction.harmony.FirstFret;
import org.curtis.musicxml.direction.harmony.Frame;
import org.curtis.musicxml.direction.harmony.FrameNote;
import org.curtis.musicxml.direction.harmony.Function;
import org.curtis.musicxml.direction.harmony.Harmony;
import org.curtis.musicxml.direction.harmony.HarmonyChord;
import org.curtis.musicxml.direction.harmony.Inversion;
import org.curtis.musicxml.direction.harmony.Kind;
import org.curtis.musicxml.direction.harmony.Root;
import org.curtis.musicxml.direction.harmony.RootAlter;
import org.curtis.musicxml.direction.harmony.RootStep;

import java.util.HashMap;
import java.util.Map;

public class HarmonyBuilder extends BaseBuilder {
    private Harmony harmony;

    public HarmonyBuilder(Harmony harmony) {
        this.harmony = harmony;
    }

    public StringBuilder build() {
        append("<harmony");
        buildAttribute("type", BuilderUtil.enumValue(harmony.getType()));
        appendLine(">");
        for (HarmonyChord harmonyChord : harmony.getHarmonyChords()) {
            if (harmonyChord instanceof Root) buildRoot((Root)harmonyChord);
            else if (harmonyChord instanceof Function) buildFunction((Function)harmonyChord);
            Kind kind = harmonyChord.getKind();
            String kindValue = BuilderUtil.enumValue(kind.getKindValue());
            kindValue = kindValue.replace("neopolitan", "Neapolitan");
            kindValue = kindValue.replace("italian", "Italian");
            kindValue = kindValue.replace("french", "French");
            kindValue = kindValue.replace("german", "German");
            kindValue = kindValue.replace("tristan", "Tristan");
            buildElementWithValueAndAttribute("kind", kindValue, "text", kind.getText());
            Inversion inversion = harmonyChord.getInversion();
            if (inversion != null) buildElementWithValue("inversion", inversion.getValue());
            Bass bass = harmonyChord.getBass();
            if (bass != null) {
                appendLine("<bass>");
                BassStep bassStep = bass.getBassStep();
                buildElementWithAttribute("bass-step", "text", bassStep.getText());
                BassAlter bassAlter = bass.getBassAlter();
                if (bassAlter != null) buildElenent("bass-alter");
                appendLine("</bass>");
            }
            for (Degree degree : harmonyChord.getDegrees()) {
                appendLine("<degree>");
                DegreeValue degreeValue = degree.getDegreeValue();
                Map<String, String> attributes = new HashMap<>();
                attributes.put("symbol", BuilderUtil.enumValue(degreeValue.getSymbol()));
                attributes.put("text", degreeValue.getText());
                buildElementWithValueAndAttributes("degree-value", degreeValue.getValue(), attributes);
                buildElenent("degree-alter");
                DegreeType degreeType = degree.getDegreeType();
                buildElementWithValueAndAttribute("degree-type", BuilderUtil.enumValue(degreeType.getValue()), "text", degreeType.getText());
                appendLine("</degree>");
            }
        }
        Frame frame = harmony.getFrame();
        if (frame != null) {
            append("<frame");
            buildAttribute("unplayed", frame.getUnplayed());
            appendLine(">");
            buildElementWithValue("frame-strings", frame.getFrameStrings());
            buildElementWithValue("frame-frets", frame.getFrameFrets());
            FirstFret firstFret = frame.getFirstFret();
            if (firstFret != null) buildElementWithValueAndAttribute("first-fret", firstFret.getValue(), "text", firstFret.getText());
            appendLine("</frame>");
        }
        for (FrameNote frameNote : frame.getFrameNotes()) {
            appendLine("<frame-note>");
            Barre barre = frameNote.getBarre();
            if (barre != null) buildElenent("barre");
            appendLine("</frame-note>");
        }
        appendLine("</harmony>");

        return stringBuilder;
    }

    private void buildRoot(Root root) {
        appendLine("<root>");
        RootStep rootStep = root.getRootStep();
        buildElementWithAttribute("root-step", "text", rootStep.getText());
        RootAlter rootAlter = root.getRootAlter();
        if (rootAlter != null) {
            buildElenent("root-alter");
        }
        appendLine("</root>");
    }

    private void buildFunction(Function function) {
        buildElenent("function");
    }
}
