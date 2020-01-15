package org.curtis.musicxml.factory;

import org.curtis.musicxml.util.TypeUtil;
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
        return (Step)FactoryUtil.enumValue(Step.class, XmlUtil.getElementText(stepElement));
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
            case "512th":
            case "256th":
            case "128th":
            case "64th":
            case "32nd":
            case "16th":
                noteTypeValue = "_" + noteTypeValue;
        }

        return (NoteTypeValue)FactoryUtil.enumValue(NoteTypeValue.class, noteTypeValue);
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
        accidentalText.setSmufl(element.getAttribute("smufl"));

        return accidentalText;
    }

    public static BeamType newBeamType(Element element) {
        if(element == null) return null;

        return (BeamType)FactoryUtil.enumValue(BeamType.class, XmlUtil.getElementText(element));
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
            figure.setEditorial(FormattingFactory.newEditorial(figureElement));
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
