package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.note.notation.Glissando;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.Slide;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.Tied;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.note.notation.TupletDot;
import org.curtis.musicxml.note.notation.TupletNumber;
import org.curtis.musicxml.note.notation.TupletPortion;
import org.curtis.musicxml.note.notation.TupletType;

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
}