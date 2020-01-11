package org.curtis.musicxml.factory;

import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.note.AccidentalText;
import org.curtis.musicxml.note.AccidentalType;
import org.curtis.musicxml.note.BeamType;
import org.curtis.musicxml.note.Figure;
import org.curtis.musicxml.note.FiguredBass;
import org.curtis.musicxml.note.NoteTypeValue;
import org.curtis.musicxml.note.Step;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.util.MathUtil;
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

        return ((AccidentalType)FactoryUtil.enumValue(AccidentalType.class, XmlUtil.getElementText(accidentalElement)));
    }

    public static AccidentalText newAccidentalText(Element element) {
        if (element == null) return null;

        AccidentalText accidentalText = new AccidentalText();
        accidentalText.setAccidentalType(NoteFactory.newAccidentalType(element));
        accidentalText.setTextFormatting(FormattingFactory.newTextFormatting(element));

        return accidentalText;
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

    public static FiguredBass newFiguredBass(Element element) {
        if (element == null) return null;

        FiguredBass figuredBass = new FiguredBass();
        List<Element> figureElements = XmlUtil.getChildElements(element, "figure");
        for (Element figureElement : figureElements) {
            Figure figure = new Figure();
            figure.setPrefix(FormattingFactory.newStyleText(XmlUtil.getChildElement(figureElement, "prefix")));
            figure.setFigureNumber(FormattingFactory.newStyleText(XmlUtil.getChildElement(figureElement, "figure-number")));
            figure.setSuffix(FormattingFactory.newStyleText(XmlUtil.getChildElement(figureElement, "suffix")));
            figure.setExtend(LyricFactory.newExtend(XmlUtil.getChildElement(figureElement, "extend")));
            figuredBass.getFigures().add(figure);
        }
        figuredBass.setDuration(MathUtil.newBigDecimal(XmlUtil.getChildElementText(element, "duration")));
        figuredBass.setEditorial(FormattingFactory.newEditorial(element));
        figuredBass.setPrintStyle(FormattingFactory.newPrintStyle(element));
        figuredBass.setPrintout(FormattingFactory.newPrintout(element));
        figuredBass.setParentheses(TypeUtil.getYesNo(element.getAttribute("parentheses")));

        return figuredBass;
    }
}
