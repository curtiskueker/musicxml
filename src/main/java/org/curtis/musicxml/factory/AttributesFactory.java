package org.curtis.musicxml.factory;

import org.curtis.musicxml.attributes.Tuning;
import org.curtis.musicxml.attributes.key.Cancel;
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
            cancel.setLocation(FactoryUtil.enumValue(CancelLocation.class, cancelElement.getAttribute("location")));
            traditionalKey.setCancel(cancel);
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
                    interchangeable.setTimeRelation(FactoryUtil.enumValue(TimeRelation.class, XmlUtil.getChildElementText(timeSubelement, "time-relation")));
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
