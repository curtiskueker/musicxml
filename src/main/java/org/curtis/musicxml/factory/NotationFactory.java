package org.curtis.musicxml.factory;

import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.note.AccidentalType;
import org.curtis.musicxml.note.LineShape;
import org.curtis.musicxml.note.NoteTypeValue;
import org.curtis.musicxml.note.Step;
import org.curtis.musicxml.note.notation.ShowTuplet;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.note.notation.TupletDot;
import org.curtis.musicxml.note.notation.TupletNumber;
import org.curtis.musicxml.note.notation.TupletPortion;
import org.curtis.musicxml.note.notation.TupletType;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class NotationFactory {
    private NotationFactory() {

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

    public static Tuplet newTuplet(Element tupletElement) {
        if(tupletElement == null) {
            return null;
        }

        Tuplet tuplet = new Tuplet();

        TupletPortion tupletActual = newTupletPortion(XmlUtil.getChildElement(tupletElement, "tuplet-actual"));
        tuplet.setTupletActual(tupletActual);
        TupletPortion tupletNormal = newTupletPortion(XmlUtil.getChildElement(tupletElement, "tuplet-normal"));
        tuplet.setTupletNormal(tupletNormal);
        tuplet.setType(PlacementUtil.getConnection(tupletElement.getAttribute("type")));
        tuplet.setNumber(StringUtil.getInteger(tupletElement.getAttribute("number")));
        tuplet.setBracket(TypeUtil.getYesNo(tupletElement.getAttribute("bracket")));
        tuplet.setShowNumber(newShowTuplet(tupletElement.getAttribute("show-number")));
        tuplet.setShowType(newShowTuplet(tupletElement.getAttribute("show-type")));
        String lineShape = tupletElement.getAttribute("line-shape");
        if(StringUtil.isNotEmpty(lineShape)) {
            switch (lineShape) {
                case "straight":
                    tuplet.setLineShape(LineShape.STRAIGHT);
                    break;
                case "curved":
                    tuplet.setLineShape(LineShape.CURVED);
                    break;
            }
        }
        tuplet.setPosition(FormattingFactory.newPosition(tupletElement));
        tuplet.setPlacement(PlacementUtil.getLocation(tupletElement.getAttribute("placement")));

        return tuplet;
    }

    public static TupletPortion newTupletPortion(Element tupletPortionElement) {
        if(tupletPortionElement == null) {
            return null;
        }

        TupletPortion tupletPortion = new TupletPortion();

        Element tupletNumberElement = XmlUtil.getChildElement(tupletPortionElement, "tuplet-number");
        if (tupletNumberElement != null) {
            TupletNumber tupletNumber = new TupletNumber();
            tupletNumber.setValue(StringUtil.getInteger(XmlUtil.getElementText(tupletNumberElement)));
            tupletNumber.setFont(FormattingFactory.newFont(tupletNumberElement));
            tupletNumber.setColor(tupletNumberElement.getAttribute("color"));
            tupletPortion.setTupletNumber(tupletNumber);
        }

        Element tupletTypeElement = XmlUtil.getChildElement(tupletNumberElement, "tuplet-type");
        if (tupletTypeElement != null) {
            TupletType tupletType = new TupletType();
            tupletType.setNoteTypeValue(newNoteTypeValue(tupletTypeElement));
            tupletType.setFont(FormattingFactory.newFont(tupletTypeElement));
            tupletType.setColor(tupletTypeElement.getAttribute("color"));
            tupletPortion.setTupletType(tupletType);
        }

        List<Element> tupletDotElements = XmlUtil.getChildElements(tupletPortionElement, "tuplet-dots");
        List<TupletDot> tupletDots = tupletPortion.getTupletDots();
        for(Element tupletDotElement : tupletDotElements) {
            TupletDot tupletDot = new TupletDot();
            tupletDot.setFont(FormattingFactory.newFont(tupletDotElement));
            tupletDot.setColor(tupletDotElement.getAttribute("color"));
            tupletDots.add(tupletDot);
        }

        return tupletPortion;
    }

    public static ShowTuplet newShowTuplet(String showTupletValue) {
        if(StringUtil.isEmpty(showTupletValue)) return null;
        switch (showTupletValue) {
            case "actual":
                return ShowTuplet.ACTUAL;
            case "both":
                return ShowTuplet.BOTH;
            case "none":
                return ShowTuplet.NONE;
        }

        return null;
    }
}
