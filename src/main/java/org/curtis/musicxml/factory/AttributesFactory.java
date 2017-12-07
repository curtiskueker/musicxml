package org.curtis.musicxml.factory;

import org.curtis.musicxml.attributes.TimeSeparator;
import org.curtis.musicxml.attributes.TimeSignature;
import org.curtis.musicxml.attributes.TimeSymbol;
import org.curtis.musicxml.attributes.Tuning;
import org.curtis.musicxml.attributes.measure.SlashGroup;
import org.curtis.musicxml.score.GroupSymbolType;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class AttributesFactory {
    private AttributesFactory() {

    }

    public static TimeSignature newTimeSignature(Element element) {
        if(element == null) return null;

        TimeSignature timeSignature = new TimeSignature();
        timeSignature.setBeats(XmlUtil.getChildElementText(element, "beats"));
        timeSignature.setBeatType(XmlUtil.getChildElementText(element, "beat-type"));

        return timeSignature;
    }

    public static TimeSymbol newTimeSymbol(Element element) {
        if(element == null) return null;

        String symbol = element.getAttribute("symbol");
        if(StringUtil.isEmpty(symbol)) return null;

        switch (symbol) {
            case "common":
            return TimeSymbol.COMMON;
            case "cut":
                return TimeSymbol.CUT;
            case "single-number":
                return TimeSymbol.SINGLE_NUMBER;
            case "note":
                return TimeSymbol.NOTE;
            case "dotted-note":
                return TimeSymbol.DOTTED_NOTE;
            case "normal":
                return TimeSymbol.NORMAL;
            default:
                return null;
        }
    }

    public static TimeSeparator newTimeSeparator(Element element) {
        if(element == null) return null;

        String separator = element.getAttribute("separator");
        if(StringUtil.isEmpty(separator)) return null;

        switch (separator) {
            case "none":
            return TimeSeparator.NONE;
            case "horizontal":
                return TimeSeparator.HORIZONTAL;
            case "diagonal":
                return TimeSeparator.DIAGONAL;
            case "vertical":
                return TimeSeparator.VERTICAL;
            case "adjacent":
                return TimeSeparator.ADJACENT;
            default:
                return null;
        }
    }

    public static GroupSymbolType newGroupSymbolType(Element element) {
        if(element == null) return null;

        String symbolValue = XmlUtil.getElementText(element);
        if(StringUtil.isEmpty(symbolValue)) return null;

        switch (symbolValue) {
            case "none":
            return GroupSymbolType.NONE;
            case "brace":
                return GroupSymbolType.BRACE;
            case "line":
                return GroupSymbolType.LINE;
            case "bracket":
                return GroupSymbolType.BRACKET;
            case "square":
                return GroupSymbolType.SQUARE;
            default:
                return null;
        }
    }

    public static Tuning newTuning(Element element) {
        if(element == null) return null;

        Tuning tuning = new Tuning();
        tuning.setTuningStep(NoteFactory.newStep(element));
        tuning.setTuningAlter(MathUtil.newBigDecimal(XmlUtil.getChildElementText(element, "tuning-alter")));
        tuning.setTuningOctave(StringUtil.getInteger(XmlUtil.getChildElementText(element, "tuning-octave")));

        return tuning;
    }

    public static SlashGroup newSlashGroup(Element element) {
        if(element == null) return null;

        SlashGroup slashGroup = new SlashGroup();
        slashGroup.setSlashType(NoteFactory.newNoteTypeValue(XmlUtil.getChildElement(element, "slash-type")));
        List<Element> slashDotElements = XmlUtil.getChildElements(element, "slash-dot");
        for (Element slashDotElement : slashDotElements) {
            Integer slashGroupDots = slashGroup.getSlashDots();
            slashGroupDots++;
            slashGroup.setSlashDots(slashGroupDots);
        }

        return slashGroup;
    }
}
