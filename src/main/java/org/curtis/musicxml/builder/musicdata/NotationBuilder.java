package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.builder.PlacementBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.note.notation.AccidentalMark;
import org.curtis.musicxml.note.notation.Arpeggiate;
import org.curtis.musicxml.note.notation.Articulations;
import org.curtis.musicxml.note.notation.Bezier;
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

public class NotationBuilder extends BaseBuilder {
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
        else if (notation instanceof Arpeggiate) buildArpeggiate((Arpeggiate)notation);
        else if (notation instanceof NonArpeggiate) buildNonArpeggiate((NonArpeggiate)notation);
        else if (notation instanceof AccidentalMark) buildAccidentalMark((AccidentalMark)notation);
        else if (notation instanceof OtherNotation) buildOtherNotation((OtherNotation)notation);

        return stringBuilder;
    }

    private void buildTied(Tied tied) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(tied.getType()));
        attributes.put("number", BuilderUtil.stringValue(tied.getNumber()));
        attributes.putAll(PlacementBuilder.buildPosition(tied.getPosition()));
        attributes.put("placement", BuilderUtil.enumValue(tied.getPlacement()));
        attributes.put("orientation", BuilderUtil.enumValue(tied.getOrientation()));
        attributes.putAll(buildBezier(tied.getBezier()));
        attributes.put("color", tied.getColor());
        buildElementWithAttributes("tied", attributes);
    }

    private void buildSlur(Slur slur) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(slur.getConnectionType()));
        attributes.put("number", BuilderUtil.stringValue(slur.getNumber()));
        attributes.putAll(PlacementBuilder.buildPosition(slur.getPosition()));
        attributes.put("placement", BuilderUtil.enumValue(slur.getPlacement()));
        attributes.put("orientation", BuilderUtil.enumValue(slur.getOrientation()));
        attributes.putAll(buildBezier(slur.getBezier()));
        attributes.put("color", slur.getColor());
        buildElementWithAttributes("slur", attributes);
    }

    private Map<String, String> buildBezier(Bezier bezier) {
        Map<String, String> attributes = new HashMap<>();
        if (bezier == null) return attributes;

        attributes.put("bezier-offset", BuilderUtil.stringValue(bezier.getBezierOffset()));
        attributes.put("bezier-offset2", BuilderUtil.stringValue(bezier.getBezierOffset2()));
        attributes.put("bezier-x", BuilderUtil.stringValue(bezier.getBezierX()));
        attributes.put("bezier-y", BuilderUtil.stringValue(bezier.getBezierY()));
        attributes.put("bezier-x2", BuilderUtil.stringValue(bezier.getBezierX2()));
        attributes.put("bezier-y2", BuilderUtil.stringValue(bezier.getBezierY2()));

        return attributes;
    }

    private void buildTuplet(Tuplet tuplet) {
        append("<tuplet");
        buildAttribute("type", BuilderUtil.enumValue(tuplet.getType()));
        buildAttribute("number", BuilderUtil.stringValue(tuplet.getNumber()));
        buildAttribute("bracket", BuilderUtil.yesOrNo(tuplet.getBracket()));
        buildAttribute("show-number", BuilderUtil.enumValue(tuplet.getShowNumber()));
        buildAttribute("show-type", BuilderUtil.enumValue(tuplet.getShowType()));
        buildAttribute("line-shape", BuilderUtil.enumValue(tuplet.getLineShape()));
        PlacementBuilder.buildPosition(tuplet.getPosition()).forEach((k, v) -> buildAttribute(k, v));
        buildAttribute("placement", BuilderUtil.enumValue(tuplet.getPlacement()));
        appendLine(">");
        TupletPortion tupletActual = tuplet.getTupletActual();
        if (tupletActual != null) {
            appendLine("<tuplet-actual>");
            buildTupletPortion(tupletActual);
            appendLine("</tuplet-actual>");
        }
        TupletPortion tupletNormal = tuplet.getTupletNormal();
        if (tupletNormal != null) {
            appendLine("<tuplet-normal>");
            buildTupletPortion(tupletNormal);
            appendLine("</tuplet-normal>");
        }
        appendLine("</tuplet>");
    }

    private void buildTupletPortion(TupletPortion tupletPortion) {
        TupletNumber tupletNumber = tupletPortion.getTupletNumber();
        if (tupletNumber != null) {
            Map<String, String> tupletNumberAttributes = new HashMap<>();
            tupletNumberAttributes.putAll(FormattingBuilder.buildFont(tupletNumber.getFont()));
            tupletNumberAttributes.put("color", tupletNumber.getColor());
            buildElementWithValueAndAttributes("tuplet-number", tupletNumber.getValue(), tupletNumberAttributes);
        }
        TupletType tupletType = tupletPortion.getTupletType();
        if (tupletType != null) {
            Map<String, String> tupletTypeAttributes = new HashMap<>();
            tupletTypeAttributes.putAll(FormattingBuilder.buildFont(tupletType.getFont()));
            tupletTypeAttributes.put("color", tupletType.getColor());
            buildElementWithValueAndAttributes("tuplet-type", BuilderUtil.noteTypeValue(tupletType.getNoteTypeValue()), tupletTypeAttributes);
        }
        for (TupletDot tupletDot : tupletPortion.getTupletDots()) {
            Map<String, String> tupletDotAttributes = new HashMap<>();
            tupletDotAttributes.putAll(FormattingBuilder.buildFont(tupletDot.getFont()));
            tupletDotAttributes.put("color", tupletDot.getColor());
            buildElementWithAttributes("tuplet-dot", tupletDotAttributes);
        }
    }

    private void buildGlissando(Glissando glissando) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(glissando.getType()));
        attributes.put("number", BuilderUtil.stringValue(glissando.getNumber()));
        attributes.putAll(FormattingBuilder.buildPrintStyle(glissando.getPrintStyle()));
        buildElementWithValueAndAttributes("glissando", glissando.getValue(), attributes);
    }

    private void buildSlide(Slide slide) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(slide.getType()));
        attributes.put("number", BuilderUtil.stringValue(slide.getNumber()));
        attributes.putAll(FormattingBuilder.buildPrintStyle(slide.getPrintStyle()));
        buildElementWithValueAndAttributes("slide", slide.getValue(), attributes);
    }

    private void buildOrnaments(Ornaments ornaments) {
        appendLine("<ornaments>");
        List<Ornament> ornamentList = ornaments.getOrnaments();
        for (Ornament ornament : ornamentList) {
            OrnamentBuilder ornamentBuilder = new OrnamentBuilder(ornament);
            append(ornamentBuilder.build().toString());
        }
        appendLine("</ornaments>");
    }

    private void buildTechnicals(Technicals technicals) {
        appendLine("<technical>");
        for (Technical technical : technicals.getTechnicals()) {
            TechnicalBuilder technicalBuilder = new TechnicalBuilder(technical);
            append(technicalBuilder.build().toString());
        }
        appendLine("</technical>");
    }

    private void buildArticulations(Articulations articulations) {
        appendLine("<articulations>");
        for (Articulation articulation : articulations.getArticulationList()) {
            ArticulationBuilder articulationBuilder = new ArticulationBuilder(articulation);
            append(articulationBuilder.build().toString());
        }
        appendLine("</articulations>");
    }

    private void buildArpeggiate(Arpeggiate arpeggiate) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("number", BuilderUtil.stringValue(arpeggiate.getNumber()));
        attributes.put("direction", BuilderUtil.enumValue(arpeggiate.getDirection()));
        attributes.putAll(PlacementBuilder.buildPosition(arpeggiate.getPosition()));
        attributes.put("placement", BuilderUtil.enumValue(arpeggiate.getPlacement()));
        attributes.put("color", arpeggiate.getColor());
        buildElementWithAttributes("arpeggiate", attributes);
    }

    private void buildNonArpeggiate(NonArpeggiate nonArpeggiate) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(nonArpeggiate.getType()));
        attributes.put("number", BuilderUtil.stringValue(nonArpeggiate.getNumber()));
        attributes.putAll(PlacementBuilder.buildPosition(nonArpeggiate.getPosition()));
        attributes.put("placement", BuilderUtil.enumValue(nonArpeggiate.getPlacement()));
        attributes.put("color", nonArpeggiate.getColor());
        buildElementWithAttributes("non-arpeggiate", attributes);
    }

    private void buildAccidentalMark(AccidentalMark accidentalMark) {
        Map<String, String> attributes = new HashMap<>();
        attributes.putAll(FormattingBuilder.buildPrintStyle(accidentalMark.getPrintStyle()));
        attributes.put("placement", BuilderUtil.enumValue(accidentalMark.getPlacement()));
        buildElementWithAttributes("accidental-mark", attributes);
    }

    private void buildOtherNotation(OtherNotation otherNotation) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(otherNotation.getType()));
        attributes.put("number", BuilderUtil.stringValue(otherNotation.getNumber()));
        attributes.putAll(FormattingBuilder.buildPrintStyle(otherNotation.getPrintStyle()));
        attributes.put("placement", BuilderUtil.enumValue(otherNotation.getPlacement()));
        buildElementWithValueAndAttributes("other-notation", otherNotation.getValue(), attributes);
    }
}
