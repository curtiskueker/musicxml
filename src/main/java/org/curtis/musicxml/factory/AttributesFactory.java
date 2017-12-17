package org.curtis.musicxml.factory;

import org.curtis.musicxml.attributes.time.Interchangeable;
import org.curtis.musicxml.attributes.time.SenzaMisura;
import org.curtis.musicxml.attributes.time.Time;
import org.curtis.musicxml.attributes.time.TimeRelation;
import org.curtis.musicxml.attributes.time.TimeSeparator;
import org.curtis.musicxml.attributes.time.TimeSignature;
import org.curtis.musicxml.attributes.time.TimeSignatureType;
import org.curtis.musicxml.attributes.time.TimeSymbol;
import org.curtis.musicxml.attributes.Tuning;
import org.curtis.musicxml.attributes.key.Cancel;
import org.curtis.musicxml.attributes.key.CancelLocation;
import org.curtis.musicxml.attributes.key.Key;
import org.curtis.musicxml.attributes.key.NonTraditionalKey;
import org.curtis.musicxml.attributes.key.NonTraditionalKeyType;
import org.curtis.musicxml.attributes.key.TraditionalKey;
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

    public static Key newKey(Element element) {
        if(element == null) return null;

        Element fifthsElement = XmlUtil.getChildElement(element, "fifths");
        Element keyStepElement = XmlUtil.getChildElement(element, "key-step");
        if (fifthsElement != null) return newTraditionalKey(element);
        else if (keyStepElement != null) return newNonTraditionalKey(element);
        else return null;
    }

    private static TraditionalKey newTraditionalKey(Element element) {
        TraditionalKey traditionalKey = new TraditionalKey();
        Element cancelElement = XmlUtil.getChildElement(element, "cancel");
        if(cancelElement != null) {
            Cancel cancel = new Cancel();
            cancel.setFifths(StringUtil.getInteger(XmlUtil.getElementText(cancelElement)));
            String location = cancelElement.getAttribute("location");
            if(StringUtil.isNotEmpty(location)) {
                switch (location) {
                    case "left":
                        cancel.setLocation(CancelLocation.LEFT);
                        break;
                    case "right":
                        cancel.setLocation(CancelLocation.RIGHT);
                        break;
                    case "before-barline":
                        cancel.setLocation(CancelLocation.BEFORE_BARLINE);
                        break;
                }
            }
        }
        traditionalKey.setFifths(StringUtil.getInteger(XmlUtil.getChildElementText(element, "fifths")));
        traditionalKey.setMode(XmlUtil.getChildElementText(element, "mode"));

        return traditionalKey;
    }

    private static NonTraditionalKey newNonTraditionalKey(Element element) {
        NonTraditionalKey nonTraditionalKey = new NonTraditionalKey();
        List<NonTraditionalKeyType> nonTraditionalKeyList = nonTraditionalKey.getNonTraditionalKeyList();
        List<Element> nonTraditionalKeyElements = XmlUtil.getChildElements(element);
        NonTraditionalKeyType nonTraditionalKeyType = new NonTraditionalKeyType();
        for(Element nonTraditionalKeyElement : nonTraditionalKeyElements) {
            String nonTraditionalKeyElementName = nonTraditionalKeyElement.getTagName();
            switch (nonTraditionalKeyElementName) {
                case "key-step":
                    nonTraditionalKeyType = new NonTraditionalKeyType();
                    nonTraditionalKeyType.setKeyStep(NoteFactory.newStep(nonTraditionalKeyElement));
                    nonTraditionalKeyList.add(nonTraditionalKeyType);
                    break;
                case "key-alter":
                    nonTraditionalKeyType.setKeyAlter(MathUtil.newBigDecimal(XmlUtil.getElementText(nonTraditionalKeyElement)));
                    break;
                case "key-accidental":
                    nonTraditionalKeyType.setKeyAccidental(NoteFactory.newAccidentalType(nonTraditionalKeyElement));
                    break;
            }
        }

        return nonTraditionalKey;
    }

    public static Time newTime(Element element) {
        if(element == null) return null;

        Element timeSignatureElement = XmlUtil.getChildElement(element, "beats");
        Element senzaMisuraElement = XmlUtil.getChildElement(element, "senza-misura");
        if (timeSignatureElement != null) return newTimeSignature(element);
        else if(senzaMisuraElement != null) {
            SenzaMisura senzaMisura = new SenzaMisura();
            senzaMisura.setValue(XmlUtil.getElementText(senzaMisuraElement));
            return senzaMisura;
        }
        else return null;
    }

    private static TimeSignature newTimeSignature(Element element) {
        TimeSignature timeSignature = new TimeSignature();
        List<TimeSignatureType> timeSignatureList = timeSignature.getTimeSignatureList();
        List<Element> timeSubelements = XmlUtil.getChildElements(element);
        TimeSignatureType timeSignatureType = new TimeSignatureType();
        for(Element timeSubelement : timeSubelements) {
            String timeSubelementName = timeSubelement.getTagName();
            switch (timeSubelementName) {
                case "beats":
                    timeSignatureType = new TimeSignatureType();
                    timeSignatureList.add(timeSignatureType);
                    timeSignatureType.setBeats(XmlUtil.getElementText(timeSubelement));
                    break;
                case "beat-type":
                    timeSignatureType.setBeatType(XmlUtil.getElementText(timeSubelement));
                    break;
                case "interchangeable":
                    Interchangeable interchangeable = new Interchangeable();
                    String timeRelation = XmlUtil.getChildElementText(timeSubelement, "time-relation");
                    if(StringUtil.isNotEmpty(timeRelation)) {
                        switch (timeRelation) {
                            case "parentheses":
                                interchangeable.setTimeRelation(TimeRelation.PARENTHESES);
                                break;
                            case "bracket":
                                interchangeable.setTimeRelation(TimeRelation.BRACKET);
                                break;
                            case "equals":
                                interchangeable.setTimeRelation(TimeRelation.EQUALS);
                                break;
                            case "slash":
                                interchangeable.setTimeRelation(TimeRelation.SLASH);
                                break;
                            case "space":
                                interchangeable.setTimeRelation(TimeRelation.SPACE);
                                break;
                            case "hyphen":
                                interchangeable.setTimeRelation(TimeRelation.HYPHEN);
                                break;
                        }
                    }
                    interchangeable.setTimeSignature(newTimeSignature(timeSubelement));
                    interchangeable.setSymbol(AttributesFactory.newTimeSymbol(timeSubelement));
                    interchangeable.setSeparator(AttributesFactory.newTimeSeparator(timeSubelement));
                    break;
            }
        }

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
