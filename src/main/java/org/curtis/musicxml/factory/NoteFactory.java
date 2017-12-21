package org.curtis.musicxml.factory;

import org.curtis.musicxml.note.AccidentalType;
import org.curtis.musicxml.note.BeamType;
import org.curtis.musicxml.note.NoteTypeValue;
import org.curtis.musicxml.note.Step;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class NoteFactory {
    private NoteFactory() {

    }

    public static Step newStep(Element stepElement) {
        String stepValue = XmlUtil.getElementText(stepElement);
        switch (stepValue) {
            case "A":
                return Step.A;
            case "B":
                return Step.B;
            case "C":
                return Step.C;
            case "D":
                return Step.D;
            case "E":
                return Step.E;
            case "F":
                return Step.F;
            case "G":
                return Step.G;
            default:
                return null;
        }
    }

    public static NoteTypeValue newNoteTypeValue(Element noteTypeElement) {
        if(noteTypeElement == null) {
            return null;
        }

        String noteTypeValue = XmlUtil.getElementText(noteTypeElement);
        if(StringUtil.isEmpty(noteTypeValue)) {
            return null;
        }

        switch (noteTypeValue) {
            case "1024th":
                return NoteTypeValue._1024TH;
            case "512th":
                return NoteTypeValue._512TH;
            case "256th":
                return NoteTypeValue._256TH;
            case "128th":
                return NoteTypeValue._128TH;
            case "64th":
                return NoteTypeValue._64TH;
            case "32nd":
                return NoteTypeValue._32ND;
            case "16th":
                return NoteTypeValue._16TH;
            case "eighth":
                return NoteTypeValue.EIGHTH;
            case "quarter":
                return NoteTypeValue.QUARTER;
            case "half":
                return NoteTypeValue.HALF;
            case "whole":
                return NoteTypeValue.WHOLE;
            case "breve":
                return NoteTypeValue.BREVE;
            case "long":
                return NoteTypeValue.LONG;
            case "maxima":
                return NoteTypeValue.MAXIMA;
        }

        return null;
    }

    public static AccidentalType newAccidentalType(Element accidentalElement) {
        if(accidentalElement == null) {
            return null;

        }

        switch (XmlUtil.getElementText(accidentalElement)) {
            case "sharp":
                return AccidentalType.SHARP;
            case "natural":
                return AccidentalType.NATURAL;
            case "flat":
                return AccidentalType.FLAT;
            case "double-sharp":
                return AccidentalType.DOUBLE_SHARP;
            case "sharp-sharp":
                return AccidentalType.SHARP_SHARP;
            case "flat-flat":
                return AccidentalType.FLAT_FLAT;
            case "natural-sharp":
                return AccidentalType.NATURAL_SHARP;
            case "natural-flat":
                return AccidentalType.NATURAL_FLAT;
            case "quarter-flat":
                return AccidentalType.QUARTER_FLAT;
            case "quarter-sharp":
                return AccidentalType.QUARTER_SHARP;
            case "three-quarters-flat":
                return AccidentalType.THREE_QUARTERS_FLAT;
            case "three-quarters-sharp":
                return AccidentalType.THREE_QUARTERS_SHARP;
            case "sharp-down":
                return AccidentalType.SHARP_DOWN;
            case "sharp-up":
                return AccidentalType.SHARP_UP;
            case "natural-down":
                return AccidentalType.NATURAL_DOWN;
            case "natural-up":
                return AccidentalType.NATURAL_UP;
            case "flat-down":
                return AccidentalType.FLAT_DOWN;
            case "flat-up":
                return AccidentalType.FLAT_UP;
            case "triple-sharp":
                return AccidentalType.THIPLE_SHARP;
            case "triple-flat":
                return AccidentalType.THIPLE_FLAT;
            case "slash-quarter-sharp":
                return AccidentalType.SLASH_QUARTER_SHARP;
            case "slash-sharp":
                return AccidentalType.SLASH_SHARP;
            case "slash-flat":
                return AccidentalType.SLASH_FLAT;
            case "double-slash-flat":
                return AccidentalType.DOUBLE_SLASH_FLAT;
            case "sharp-1":
                return AccidentalType.SHARP_1;
            case "sharp-2":
                return AccidentalType.SHARP_2;
            case "sharp-3":
                return AccidentalType.SHARP_3;
            case "sharp-5":
                return AccidentalType.SHARP_5;
            case "flat-1":
                return AccidentalType.FLAT_1;
            case "flat-2":
                return AccidentalType.FLAT_2;
            case "flat-3":
                return AccidentalType.FLAT_3;
            case "flat-4":
                return AccidentalType.FLAT_4;
            case "sori":
                return AccidentalType.SORI;
            case "koron":
                return AccidentalType.KORON;
            default:
                return null;
        }
    }

    public static BeamType newBeamType(Element element) {
        if(element == null) return null;

        switch (XmlUtil.getElementText(element)) {
            case "begin":
                return BeamType.BEGIN;
            case "continue":
                return BeamType.CONTINUE;
            case "end":
                return BeamType.END;
            case "forward hook":
                return BeamType.FORWARD_HOOK;
            case "backward hook":
                return BeamType.BACKWARD_HOOK;
            default:
                return null;
        }
    }

    public static TimeModification newTimeModification(Element element) {
        if(element == null) return null;

        TimeModification timeModification = new TimeModification();
        timeModification.setActualNotes(StringUtil.getInteger(XmlUtil.getChildElementText(element, "actual-notes")));
        timeModification.setNormalNotes(StringUtil.getInteger(XmlUtil.getChildElementText(element, "normal-notes")));
        timeModification.setNormalType(NoteFactory.newNoteTypeValue(XmlUtil.getChildElement(element, "normal-type")));
        List<Element> dotElements = XmlUtil.getChildElements(element, "normal-dot");
        timeModification.setNormalDots(dotElements.size());

        return timeModification;
    }
}
