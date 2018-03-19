package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.note.notation.AccidentalMark;
import org.curtis.musicxml.note.notation.Arpeggiate;
import org.curtis.musicxml.note.notation.Articulations;
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

import java.util.List;

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
        // TODO: tied type
        buildElementWithAttribute("tied", "type", "start");
    }

    private void buildSlur(Slur slur) {
        // TODO: slur type
        buildElementWithAttribute("slur", "type", "start");
    }

    private void buildTuplet(Tuplet tuplet) {
        append("<tuplet");
        //TODO: tuplet type
        buildAttribute("type", "start");
        buildAttribute("show-number", BuilderUtil.enumValue(tuplet.getShowNumber()));
        buildAttribute("show-type", BuilderUtil.enumValue(tuplet.getShowType()));
        buildAttribute("line-shape", BuilderUtil.enumValue(tuplet.getLineShape()));
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
        if (tupletNumber != null) buildElementWithValue("tuplet-number", tupletNumber.getValue());
        TupletType tupletType = tupletPortion.getTupletType();
        if (tupletType != null) buildElementWithValue("tuplet-type", BuilderUtil.noteTypeValue(tupletType.getNoteTypeValue()));
        for (TupletDot tupletDot : tupletPortion.getTupletDots()) buildElement("tuplet-dot");
    }

    private void buildGlissando(Glissando glissando) {
        buildElementWithValue("glissando", glissando.getValue());
    }

    private void buildSlide(Slide slide) {
        buildElementWithValue("slide", slide.getValue());
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
        buildElement("arpeggiate");
    }

    private void buildNonArpeggiate(NonArpeggiate nonArpeggiate) {
        buildElement("non-arpeggiate");
    }

    private void buildAccidentalMark(AccidentalMark accidentalMark) {
        buildElement("accidental-mark");
    }

    private void buildOtherNotation(OtherNotation otherNotation) {
        buildElementWithValue("other-notation", otherNotation.getValue());
    }
}
