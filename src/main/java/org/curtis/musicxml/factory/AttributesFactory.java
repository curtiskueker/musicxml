package org.curtis.musicxml.factory;

import org.curtis.musicxml.attributes.Tuning;
import org.curtis.musicxml.attributes.key.CancelLocation;
import org.curtis.musicxml.attributes.key.Key;
import org.curtis.musicxml.attributes.key.NonTraditionalKey;
import org.curtis.musicxml.attributes.key.NonTraditionalKeyType;
import org.curtis.musicxml.attributes.key.TraditionalKey;
import org.curtis.musicxml.attributes.measure.ExceptVoice;
import org.curtis.musicxml.attributes.measure.SlashGroup;
import org.curtis.musicxml.attributes.time.Interchangeable;
import org.curtis.musicxml.attributes.time.SenzaMisura;
import org.curtis.musicxml.attributes.time.Time;
import org.curtis.musicxml.attributes.time.TimeRelation;
import org.curtis.musicxml.attributes.time.TimeSeparator;
import org.curtis.musicxml.attributes.time.TimeSignature;
import org.curtis.musicxml.attributes.time.TimeSignatureType;
import org.curtis.musicxml.attributes.time.TimeSymbol;
import org.curtis.musicxml.score.GroupSymbolType;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.ArrayList;
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
            traditionalKey.setCancelFifths(StringUtil.getInteger(XmlUtil.getElementText(cancelElement)));
            traditionalKey.setCancelLocation(FactoryUtil.enumValue(CancelLocation.class, cancelElement.getAttribute("location")));
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
                    nonTraditionalKeyType.setKeyAccidentalSmufl(nonTraditionalKeyElement.getAttribute("smufl"));
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
        timeSignature.setTimeSignatureList(newTimeSignatureTypes(element));
        Element interchangeableElement = XmlUtil.getChildElement(element, "interchangeable");
        if (interchangeableElement != null) {
            Interchangeable interchangeable = new Interchangeable();
            interchangeable.setTimeRelation(FactoryUtil.enumValue(TimeRelation.class, XmlUtil.getChildElementText(interchangeableElement, "time-relation")));
            interchangeable.setTimeSignatureList(newTimeSignatureTypes(element));
            interchangeable.setSymbol(AttributesFactory.newTimeSymbol(interchangeableElement));
            interchangeable.setSeparator(AttributesFactory.newTimeSeparator(interchangeableElement));
            timeSignature.setInterchangeable(interchangeable);
        }

        return timeSignature;
    }

    private static List<TimeSignatureType> newTimeSignatureTypes(Element element) {
        List<TimeSignatureType> timeSignatureTypes = new ArrayList<>();

        List<Element> beatsElements = XmlUtil.getChildElements(element, "beats");
        List<Element> beatTypeElements = XmlUtil.getChildElements(element, "beat-type");

        if (beatsElements.isEmpty() || beatTypeElements.isEmpty()) return timeSignatureTypes;
        if (beatsElements.size() != beatTypeElements.size()) return timeSignatureTypes;

        for(int elementIndex = 0; elementIndex < beatsElements.size(); elementIndex++) {
            Element beatElement = beatsElements.get(elementIndex);
            Element beatTypeElement = beatTypeElements.get(elementIndex);

            TimeSignatureType timeSignatureType = new TimeSignatureType();
            timeSignatureType.setBeats(XmlUtil.getElementText(beatElement));
            timeSignatureType.setBeatType(XmlUtil.getElementText(beatTypeElement));
            timeSignatureTypes.add(timeSignatureType);
        }

        return timeSignatureTypes;
    }

    public static TimeSymbol newTimeSymbol(Element element) {
        if(element == null) return null;

        return FactoryUtil.enumValue(TimeSymbol.class, element.getAttribute("symbol"));
    }

    public static TimeSeparator newTimeSeparator(Element element) {
        if(element == null) return null;

        return FactoryUtil.enumValue(TimeSeparator.class, element.getAttribute("separator"));
    }

    public static GroupSymbolType newGroupSymbolType(Element element) {
        if(element == null) return null;

        return FactoryUtil.enumValue(GroupSymbolType.class, XmlUtil.getElementText(element));
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
        for (Element exceptVoiceElement : XmlUtil.getChildElements(element, "except-voice")) {
            ExceptVoice exceptVoice = new ExceptVoice();
            exceptVoice.setValue(XmlUtil.getElementText(exceptVoiceElement));
            slashGroup.getExceptVoices().add(exceptVoice);
        }

        return slashGroup;
    }
}
