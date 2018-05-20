package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.direction.harmony.Barre;
import org.curtis.musicxml.direction.harmony.Bass;
import org.curtis.musicxml.direction.harmony.BassAlter;
import org.curtis.musicxml.direction.harmony.BassStep;
import org.curtis.musicxml.direction.harmony.Degree;
import org.curtis.musicxml.direction.harmony.DegreeAlter;
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
        buildAttribute("print-frame", BuilderUtil.yesOrNo(harmony.getPrintFrame()));
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
            Map<String, String> kindAttributes = new HashMap<>();
            kindAttributes.put("use-symbols", BuilderUtil.yesOrNo(kind.getUseSymbols()));
            kindAttributes.put("text", kind.getText());
            kindAttributes.put("stack-degrees", BuilderUtil.yesOrNo(kind.getStackDegrees()));
            kindAttributes.put("parentheses-degrees", BuilderUtil.yesOrNo(kind.getParenthesesDegrees()));
            kindAttributes.put("bracket-degrees", BuilderUtil.yesOrNo(kind.getBracketDegrees()));
            buildElementWithValueAndAttributes("kind", kindValue, kindAttributes);
            Inversion inversion = harmonyChord.getInversion();
            if (inversion != null) buildElementWithValue("inversion", inversion.getValue());
            Bass bass = harmonyChord.getBass();
            if (bass != null) {
                appendLine("<bass>");
                BassStep bassStep = bass.getBassStep();
                buildElementWithAttribute("bass-step", "text", bassStep.getText());
                BassAlter bassAlter = bass.getBassAlter();
                if (bassAlter != null) buildElement("bass-alter");
                appendLine("</bass>");
            }
            for (Degree degree : harmonyChord.getDegrees()) {
                appendLine("<degree>");
                DegreeValue degreeValue = degree.getDegreeValue();
                Map<String, String> degreeAttributes = new HashMap<>();
                degreeAttributes.put("symbol", BuilderUtil.enumValue(degreeValue.getSymbol()));
                degreeAttributes.put("text", degreeValue.getText());
                buildElementWithValueAndAttributes("degree-value", degreeValue.getValue(), degreeAttributes);
                DegreeAlter degreeAlter = degree.getDegreeAlter();
                buildElementWithAttribute("degree-alter", "plus-minus", BuilderUtil.yesOrNo(degreeAlter.getPlusMinus()));
                DegreeType degreeType = degree.getDegreeType();
                buildElementWithValueAndAttribute("degree-type", BuilderUtil.enumValue(degreeType.getValue()), "text", degreeType.getText());
                appendLine("</degree>");
            }
        }
        Frame frame = harmony.getFrame();
        if (frame != null) {
            append("<frame");
            buildAttribute("height", BuilderUtil.stringValue(frame.getHeight()));
            buildAttribute("width", BuilderUtil.stringValue(frame.getWidth()));
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
            if (barre != null) buildElementWithAttribute("barre", "type", BuilderUtil.enumValue(barre.getType()));
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
            buildElement("root-alter");
        }
        appendLine("</root>");
    }

    private void buildFunction(Function function) {
        buildElement("function");
    }
}
