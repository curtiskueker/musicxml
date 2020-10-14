package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.DisplayBuilder;
import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.builder.BuilderUtil;
import org.curtis.musicxml.note.notation.AccidentalMark;
import org.curtis.musicxml.note.notation.Arpeggiate;
import org.curtis.musicxml.note.notation.Articulations;
import org.curtis.musicxml.note.notation.Bezier;
import org.curtis.musicxml.note.notation.DynamicsNotation;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.Glissando;
import org.curtis.musicxml.note.notation.NonArpeggiate;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.Ornaments;
import org.curtis.musicxml.note.notation.OtherNotation;
import org.curtis.musicxml.note.notation.Slide;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.Technicals;
import org.curtis.musicxml.note.notation.Tied;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.note.notation.TupletDot;
import org.curtis.musicxml.note.notation.TupletNumber;
import org.curtis.musicxml.note.notation.TupletPortion;
import org.curtis.musicxml.note.notation.TupletType;
import org.curtis.musicxml.note.notation.articulation.Articulation;
import org.curtis.musicxml.note.notation.ornament.Ornament;
import org.curtis.musicxml.note.notation.technical.Technical;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotationBuilder extends MusicDataBuilder {
    private Notation notation;

    public NotationBuilder(Notation notation) {
        this.notation = notation;
    }

    public StringBuilder build() {
        if (notation == null) return stringBuilder;

        if (notation instanceof Tied) buildTied((Tied)notation);
        else if (notation instanceof Slur) buildSlur((Slur)notation);
        else if (notation instanceof Tuplet) buildTuplet((Tuplet)notation);
        else if (notation instanceof Glissando) buildGlissando((Glissando)notation);
        else if (notation instanceof Slide) buildSlide((Slide)notation);
        else if (notation instanceof Ornaments) buildOrnaments((Ornaments)notation);
        else if (notation instanceof Technicals) buildTechnicals((Technicals)notation);
        else if (notation instanceof Articulations) buildArticulations((Articulations)notation);
        else if (notation instanceof DynamicsNotation) buildDynamicsNotation((DynamicsNotation)notation);
        else if (notation instanceof Fermata) buildFermata((Fermata)notation);
        else if (notation instanceof Arpeggiate) buildArpeggiate((Arpeggiate)notation);
        else if (notation instanceof NonArpeggiate) buildNonArpeggiate((NonArpeggiate)notation);
        else if (notation instanceof AccidentalMark) buildAccidentalMark((AccidentalMark)notation);
        else if (notation instanceof OtherNotation) buildOtherNotation((OtherNotation)notation);

        return stringBuilder;
    }

    private void buildTied(Tied tied) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", tied.getElementId());
        attributes.put("type", BuilderUtil.enumValue(tied.getType()));
        attributes.put("number", BuilderUtil.stringValue(tied.getNumber()));
        attributes.put("line-type", BuilderUtil.enumValue(tied.getLineType()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(tied.getDashedFormatting()));
        attributes.putAll(DisplayBuilder.buildDisplay(tied.getDisplay()));
        attributes.put("orientation", BuilderUtil.enumValue(tied.getOrientation()));
        attributes.putAll(buildBezier(tied.getBezier()));
        buildElementWithAttributes("tied", attributes);
    }

    private void buildSlur(Slur slur) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", slur.getElementId());
        attributes.put("type", BuilderUtil.enumValue(slur.getType()));
        attributes.put("number", BuilderUtil.stringValue(slur.getNumber()));
        attributes.put("line-type", BuilderUtil.enumValue(slur.getLineType()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(slur.getDashedFormatting()));
        attributes.putAll(DisplayBuilder.buildDisplay(slur.getDisplay()));
        attributes.put("orientation", BuilderUtil.enumValue(slur.getOrientation()));
        attributes.putAll(buildBezier(slur.getBezier()));
        buildElementWithAttributes("slur", attributes);
    }

    private Map<String, String> buildBezier(Bezier bezier) {
        Map<String, String> attributes = new HashMap<>();
        if (bezier == null) return attributes;

        attributes.put("bezier-x", BuilderUtil.stringValue(bezier.getBezierX()));
        attributes.put("bezier-y", BuilderUtil.stringValue(bezier.getBezierY()));
        attributes.put("bezier-x2", BuilderUtil.stringValue(bezier.getBezierX2()));
        attributes.put("bezier-y2", BuilderUtil.stringValue(bezier.getBezierY2()));
        attributes.put("bezier-offset", BuilderUtil.stringValue(bezier.getBezierOffset()));
        attributes.put("bezier-offset2", BuilderUtil.stringValue(bezier.getBezierOffset2()));

        return attributes;
    }

    private void buildTuplet(Tuplet tuplet) {
        buildOpenElement("tuplet");
        buildAttribute("id", tuplet.getElementId());
        buildAttribute("type", BuilderUtil.enumValue(tuplet.getType()));
        buildAttribute("number", tuplet.getNumber());
        buildAttribute("bracket",  tuplet.getBracket());
        buildAttribute("show-number", tuplet.getShowNumber());
        buildAttribute("show-type", tuplet.getShowType());
        buildAttribute("line-shape", tuplet.getLineShape());
        buildAttributes(DisplayBuilder.buildDisplay(tuplet.getDisplay()));
        buildCloseElement();
        TupletPortion tupletActual = tuplet.getTupletActual();
        if (tupletActual != null) {
            buildStartElement("tuplet-actual");
            buildTupletPortion(tupletActual);
            buildEndElement("tuplet-actual");
        }
        TupletPortion tupletNormal = tuplet.getTupletNormal();
        if (tupletNormal != null) {
            buildStartElement("tuplet-normal");
            buildTupletPortion(tupletNormal);
            buildEndElement("tuplet-normal");
        }
        buildEndElement("tuplet");
    }

    private void buildTupletPortion(TupletPortion tupletPortion) {
        TupletNumber tupletNumber = tupletPortion.getTupletNumber();
        if (tupletNumber != null) buildElementWithValueAndAttributes("tuplet-number", tupletNumber.getValue(), DisplayBuilder.buildDisplay(tupletNumber.getDisplay()));
        TupletType tupletType = tupletPortion.getTupletType();
        if (tupletType != null) buildElementWithValueAndAttributes("tuplet-type", BuilderUtil.noteTypeValue(tupletType.getValue()), DisplayBuilder.buildDisplay(tupletType.getDisplay()));
        for (TupletDot tupletDot : tupletPortion.getTupletDots()) buildElementWithOptionalAttributes("tuplet-dot", DisplayBuilder.buildDisplay(tupletDot.getDisplay()));
    }

    private void buildGlissando(Glissando glissando) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", glissando.getElementId());
        attributes.put("type", BuilderUtil.enumValue(glissando.getType()));
        attributes.put("number", BuilderUtil.stringValue(glissando.getNumber()));
        attributes.put("line-type", BuilderUtil.enumValue(glissando.getLineType()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(glissando.getDashedFormatting()));
        attributes.putAll(DisplayBuilder.buildDisplay(glissando.getDisplay()));
        buildElementWithValueAndAttributes("glissando", glissando.getValue(), attributes);
    }

    private void buildSlide(Slide slide) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", slide.getElementId());
        attributes.put("type", BuilderUtil.enumValue(slide.getType()));
        attributes.put("number", BuilderUtil.stringValue(slide.getNumber()));
        attributes.put("line-type", BuilderUtil.enumValue(slide.getLineType()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(slide.getDashedFormatting()));
        attributes.putAll(DisplayBuilder.buildDisplay(slide.getDisplay()));
        attributes.putAll(TechnicalBuilder.buildBendSound(slide.getBendSound()));
        buildElementWithValueAndAttributes("slide", slide.getValue(), attributes);
    }

    private void buildOrnaments(Ornaments ornaments) {
        buildOpenElement("ornaments");
        buildAttribute("id", ornaments.getElementId());
        buildCloseElement();
        List<Ornament> ornamentList = ornaments.getOrnaments();
        for (Ornament ornament : ornamentList) {
            OrnamentBuilder ornamentBuilder = new OrnamentBuilder(ornament);
            append(ornamentBuilder.build().toString());
        }
        for (AccidentalMark accidentalMark : ornaments.getAccidentalMarks()) buildAccidentalMark(accidentalMark);
        buildEndElement("ornaments");
    }

    private void buildTechnicals(Technicals technicals) {
        buildOpenElement("technical");
        buildAttribute("id", technicals.getElementId());
        buildCloseElement();
        for (Technical technical : technicals.getTechnicals()) {
            TechnicalBuilder technicalBuilder = new TechnicalBuilder(technical);
            append(technicalBuilder.build().toString());
        }
        buildEndElement("technical");
    }

    private void buildArticulations(Articulations articulations) {
        buildOpenElement("articulations");
        buildAttribute("id", articulations.getElementId());
        buildCloseElement();
        for (Articulation articulation : articulations.getArticulationList()) {
            ArticulationBuilder articulationBuilder = new ArticulationBuilder(articulation);
            append(articulationBuilder.build().toString());
        }
        buildEndElement("articulations");
    }

    private void buildDynamicsNotation(DynamicsNotation dynamicsNotation) {
        buildDynamics(dynamicsNotation.getDynamics());
    }

    private void buildArpeggiate(Arpeggiate arpeggiate) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", arpeggiate.getElementId());
        attributes.put("number", BuilderUtil.stringValue(arpeggiate.getNumber()));
        attributes.put("direction", BuilderUtil.enumValue(arpeggiate.getDirection()));
        attributes.putAll(DisplayBuilder.buildDisplay(arpeggiate.getDisplay()));
        buildElementWithAttributes("arpeggiate", attributes);
    }

    private void buildNonArpeggiate(NonArpeggiate nonArpeggiate) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", nonArpeggiate.getElementId());
        attributes.put("type", BuilderUtil.enumValue(nonArpeggiate.getType()));
        attributes.put("number", BuilderUtil.stringValue(nonArpeggiate.getNumber()));
        attributes.putAll(DisplayBuilder.buildDisplay(nonArpeggiate.getDisplay()));
        buildElementWithAttributes("non-arpeggiate", attributes);
    }

    private void buildAccidentalMark(AccidentalMark accidentalMark) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(accidentalMark.getDisplay()));
        attributes.put("id", accidentalMark.getElementId());
        attributes.putAll(FormattingBuilder.buildLevelDisplay(accidentalMark.getLevelDisplay()));
        attributes.put("smufl", accidentalMark.getSmufl());
        buildElementWithValueAndAttributes("accidental-mark", accidentalMark.getValue(), attributes);
    }

    private void buildOtherNotation(OtherNotation otherNotation) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("id", otherNotation.getElementId());
        attributes.put("type", BuilderUtil.enumValue(otherNotation.getType()));
        attributes.put("number", BuilderUtil.stringValue(otherNotation.getNumber()));
        attributes.put("print-object", BuilderUtil.yesOrNo(otherNotation.getPrintObject()));
        attributes.putAll(DisplayBuilder.buildDisplay(otherNotation.getDisplay()));
        buildElementWithValueAndAttributes("other-notation", otherNotation.getValue(), attributes);
    }
}
