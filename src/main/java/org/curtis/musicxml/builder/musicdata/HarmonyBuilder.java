package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.builder.PlacementBuilder;
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

public class HarmonyBuilder extends MusicDataBuilder {
    private Harmony harmony;

    public HarmonyBuilder(Harmony harmony) {
        this.harmony = harmony;
    }

    public StringBuilder build() {
        buildOpenElement("harmony");
        buildAttribute("type", harmony.getType());
        buildAttribute("print-object",  harmony.getPrintObject());
        buildAttribute("print-frame",  harmony.getPrintFrame());
        buildAttributes(FormattingBuilder.buildPrintStyle(harmony.getPrintStyle()));
        buildAttribute("placement", harmony.getPlacement());
        buildCloseElement();
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
            kindAttributes.putAll(FormattingBuilder.buildPrintStyle(kind.getPrintStyle()));
            kindAttributes.put("halign", BuilderUtil.enumValue(kind.getHalign()));
            kindAttributes.put("valign", BuilderUtil.enumValue(kind.getValign()));
            buildElementWithValueAndAttributes("kind", kindValue, kindAttributes);
            Inversion inversion = harmonyChord.getInversion();
            if (inversion != null) buildElementWithValueAndAttributes("inversion", inversion.getValue(), FormattingBuilder.buildPrintStyle(inversion.getPrintStyle()));
            Bass bass = harmonyChord.getBass();
            if (bass != null) {
                buildStartElement("bass");
                BassStep bassStep = bass.getBassStep();
                Map<String, String> bassStepAttributes = new HashMap<>();
                bassStepAttributes.put("text", bassStep.getText());
                bassStepAttributes.putAll(FormattingBuilder.buildPrintStyle(bassStep.getPrintStyle()));
                buildElementWithValueAndAttributes("bass-step", BuilderUtil.enumValue(bassStep.getStep()).toUpperCase(), bassStepAttributes);
                BassAlter bassAlter = bass.getBassAlter();
                if (bassAlter != null) {
                    Map<String, String> bassAlterAttributes = new HashMap<>();
                    bassAlterAttributes.put("print-object", BuilderUtil.yesOrNo(bassAlter.getPrintObject()));
                    bassAlterAttributes.putAll(FormattingBuilder.buildPrintStyle(bassAlter.getPrintStyle()));
                    bassAlterAttributes.put("location", BuilderUtil.enumValue(bassAlter.getLocation()));
                    buildElementWithValueAndAttributes("bass-alter", bassAlter.getSemitones(), bassAlterAttributes);
                }
                buildEndElement("bass");
            }
            for (Degree degree : harmonyChord.getDegrees()) {
                buildOpenElement("degree");
                buildAttribute("print-object",  degree.getPrintObject());
                buildCloseElement();
                DegreeValue degreeValue = degree.getDegreeValue();
                Map<String, String> degreeAttributes = new HashMap<>();
                degreeAttributes.put("symbol", BuilderUtil.enumValue(degreeValue.getSymbol()));
                degreeAttributes.put("text", degreeValue.getText());
                degreeAttributes.putAll(FormattingBuilder.buildPrintStyle(degreeValue.getPrintStyle()));
                buildElementWithValueAndAttributes("degree-value", degreeValue.getValue(), degreeAttributes);
                DegreeAlter degreeAlter = degree.getDegreeAlter();
                Map<String, String> degreeAlterAttributes = new HashMap<>();
                degreeAlterAttributes.putAll(FormattingBuilder.buildPrintStyle(degreeAlter.getPrintStyle()));
                degreeAlterAttributes.put("plus-minus", BuilderUtil.yesOrNo(degreeAlter.getPlusMinus()));
                buildElementWithValueAndAttributes("degree-alter", degreeAlter.getSemitones(), degreeAlterAttributes);
                DegreeType degreeType = degree.getDegreeType();
                Map<String, String> degreeTypeAttributes = new HashMap<>();
                degreeTypeAttributes.put("text", degreeType.getText());
                degreeTypeAttributes.putAll(FormattingBuilder.buildPrintStyle(degreeType.getPrintStyle()));
                buildElementWithValueAndAttributes("degree-type", degreeType.getValue(), degreeTypeAttributes);
                buildEndElement("degree");
            }
        }
        Frame frame = harmony.getFrame();
        if (frame != null) {
            buildOpenElement("frame");
            buildAttributes(PlacementBuilder.buildPosition(frame.getPosition()));
            buildAttribute("color", frame.getColor());
            buildAttribute("halign", frame.getHalign());
            buildAttribute("valign", frame.getValignImage());
            buildAttribute("height", frame.getHeight());
            buildAttribute("width", frame.getWidth());
            buildAttribute("unplayed", frame.getUnplayed());
            buildCloseElement();
            buildElementWithValue("frame-strings", frame.getFrameStrings());
            buildElementWithValue("frame-frets", frame.getFrameFrets());
            FirstFret firstFret = frame.getFirstFret();
            if (firstFret != null) {
                Map<String, String> firstFretAttributes = new HashMap<>();
                firstFretAttributes.put("text", firstFret.getText());
                firstFretAttributes.put("location", BuilderUtil.enumValue(firstFret.getLocation()));
                buildElementWithValueAndAttributes("first-fret", firstFret.getValue(), firstFretAttributes);
            }
            for (FrameNote frameNote : frame.getFrameNotes()) {
                buildStartElement("frame-note");
                buildStringNumber(frameNote.getString());
                buildFret(frameNote.getFret());
                buildFingering(frameNote.getFingering());
                Barre barre = frameNote.getBarre();
                if (barre != null) {
                    Map<String, String> barreAttributes = new HashMap<>();
                    barreAttributes.put("type", BuilderUtil.enumValue(barre.getType()));
                    barreAttributes.put("color", barre.getColor());
                    buildElementWithAttributes("barre", barreAttributes);
                }
                buildEndElement("frame-note");
            }
            buildEndElement("frame");
        }
        append(DirectionBuilder.buildOffset(harmony.getOffset()));
        buildEditorial(harmony.getEditorial());
        buildElementWithValue("staff", harmony.getStaff());
        buildEndElement("harmony");

        return stringBuilder;
    }

    private void buildRoot(Root root) {
        buildStartElement("root");
        RootStep rootStep = root.getRootStep();
        Map<String, String> rootStepAttributes = new HashMap<>();
        rootStepAttributes.put("text", rootStep.getText());
        rootStepAttributes.putAll(FormattingBuilder.buildPrintStyle(rootStep.getPrintStyle()));
        buildElementWithValueAndAttributes("root-step", BuilderUtil.enumValue(rootStep.getStep()).toUpperCase(), rootStepAttributes);
        RootAlter rootAlter = root.getRootAlter();
        if (rootAlter != null) {
            Map<String, String> rootAlterAttributes = new HashMap<>();
            rootAlterAttributes.put("print-object", BuilderUtil.yesOrNo(rootAlter.getPrintObject()));
            rootAlterAttributes.putAll(FormattingBuilder.buildPrintStyle(rootAlter.getPrintStyle()));
            rootAlterAttributes.put("location", BuilderUtil.enumValue(rootAlter.getLocation()));
            buildElementWithValueAndAttributes("root-alter", rootAlter.getSemitones(), rootAlterAttributes);
        }
        buildEndElement("root");
    }

    private void buildFunction(Function function) {
        buildStyleText("function", function.getFunction());
    }
}
