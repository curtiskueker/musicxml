package org.curtis.musicxml.builder;

import org.curtis.musicxml.attributes.Tuning;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.common.EditorialVoice;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Level;
import org.curtis.musicxml.common.MidiDevice;
import org.curtis.musicxml.common.StyleText;
import org.curtis.musicxml.note.AccidentalText;
import org.curtis.musicxml.note.Line;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.PlacementText;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.lyric.Extend;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class OutputBuilder {
    protected StringBuilder stringBuilder = new StringBuilder();

    public StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    protected void appendLine(String string) {
        stringBuilder.append(string);
        stringBuilder.append("\n");
    }

    protected void appendLine() {
        appendLine("");
    }

    protected void append(String string) {
        stringBuilder.append(string);
    }

    protected void clear() {
        stringBuilder = new StringBuilder();
    }

    protected void buildAttribute(String attributeName, String attributeValue) {
        if (StringUtil.isEmpty(attributeValue)) return;

        append(" ");
        append(attributeName);
        append("=\"");
        append(attributeValue);
        append("\"");
    }

    protected void buildAttribute(String attributeName, Integer attributeValue) {
        if (attributeValue == null) return;

        buildAttribute(attributeName, String.valueOf(attributeValue));
    }

    protected void buildElement(String elementName) {
        buildOpenElement(elementName);
        appendLine("/>");
    }

    protected void buildOpenElement(String elementName) {
        append("<");
        append(elementName);
    }

    protected void buildCloseElement() {
        appendLine(">");
    }

    protected void buildEndElement(String elementName) {
        append("</");
        append(elementName);
        buildCloseElement();
    }

    protected void buildElementWithValue(String elementName, String elementValue) {
        if (StringUtil.isEmpty(elementValue)) return;

        buildOpenElement(elementName);
        append(">");
        append(elementValue);
        append("</");
        append(elementName);
        buildCloseElement();
    }

    protected void buildElementWithValue(String elementName, Integer elementValue) {
        if (elementValue == null) return;

        buildElementWithValue(elementName, String.valueOf(elementValue));
    }

    protected void buildElementWithValue(String elementName, BigDecimal elementValue) {
        if (elementValue == null) return;

        buildElementWithValue(elementName, elementValue.toString());
    }

    protected void buildElementWithAttribute(String elementName, String attributeName, String attributeValue) {
        if (StringUtil.isEmpty(attributeValue)) return;

        buildOpenElement(elementName);
        buildAttribute(attributeName, attributeValue);
        appendLine("/>");
    }

    protected void buildElementWithAttribute(String elementName, String attributeName, Integer attributeValue) {
        if (attributeValue == null) return;

        buildElementWithAttribute(elementName, attributeName, String.valueOf(attributeValue));
    }

    protected void buildElementWithAttributes(String elementName, Map<String, String> attributes) {
        if (attributes == null || attributes.isEmpty()) return;

        buildOpenElement(elementName);
        for (String attributeName : attributes.keySet()) {
            buildAttribute(attributeName, attributes.get(attributeName));
        }
        appendLine("/>");
    }

    protected void buildElementWithValueAndAttribute(String elementName, String elementValue, String attributeName, String attributeValue) {
        if (StringUtil.isEmpty(elementValue)) return;

        buildOpenElement(elementName);
        buildAttribute(attributeName, attributeValue);
        append(">");
        append(elementValue);
        append("</");
        append(elementName);
        buildCloseElement();
    }

    protected void buildElementWithValueAndAttribute(String elementName, String elementValue, String attributeName, Integer attributeValue) {
        buildElementWithValueAndAttribute(elementName, elementValue, attributeName, BuilderUtil.stringValue(attributeValue));
    }


    protected void buildElementWithValueAndAttribute(String elementName, Integer elementValue, String attributeName, String attributeValue) {
        if (elementValue == null) return;

        buildElementWithValueAndAttribute(elementName, String.valueOf(elementValue), attributeName, attributeValue);
    }

    protected void buildElementWithValueAndAttributes(String elementName, String elementValue, Map<String, String> attributes) {
        buildOpenElement(elementName);
        if (attributes != null && !attributes.isEmpty()) {
            for (String attributeName : attributes.keySet()) {
                buildAttribute(attributeName, attributes.get(attributeName));
            }
        }
        append(">");
        if (StringUtil.isNotEmpty(elementValue)) append(elementValue);
        append("</");
        append(elementName);
        buildCloseElement();
    }

    protected void buildElementWithValueAndAttributes(String elementName, Integer elementValue, Map<String, String> attributes) {
        buildElementWithValueAndAttributes(elementName, BuilderUtil.stringValue(elementValue), attributes);
    }

    protected void buildTimeModification(TimeModification timeModification) {
        if (timeModification == null) return;

        buildElementWithValue("actual-notes", timeModification.getActualNotes());
        buildElementWithValue("normal-notes", timeModification.getNormalNotes());
        buildElementWithValue("normal-type", BuilderUtil.noteTypeValue(timeModification.getNormalType()));
        for (int normalDots = 1; normalDots <= timeModification.getNormalDots(); normalDots++) buildElement("normal-dot");
    }

    protected void buildExtend(Extend extend) {
        if (extend == null) return;

        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(extend.getType()));
        attributes.putAll(FormattingBuilder.buildPrintStyle(extend.getPrintStyle()));
        buildElementWithAttributes("extend", attributes);
    }

    protected void buildPlacement(String elementName, Placement placement) {
        buildPlacementWithAttribute(elementName, placement, "", "");
    }

    protected void buildPlacementWithAttribute(String elementName, Placement placement, String attributeName, String attributeValue) {
        Map<String, String> attributes = new HashMap<>();
        attributes.putAll(FormattingBuilder.buildPrintStyle(placement.getPrintStyle()));
        attributes.put("placement", BuilderUtil.enumValue(placement.getPlacement()));
        if (StringUtil.isNotEmpty(attributeValue)) attributes.put(attributeName, attributeValue);
        buildElementWithAttributes(elementName, attributes);
    }

    protected void buildPlacementText(String elementName, PlacementText placementText) {
        Map<String, String> attributes = new HashMap<>();
        attributes.putAll(FormattingBuilder.buildPrintStyle(placementText.getPrintStyle()));
        attributes.put("placement", BuilderUtil.enumValue(placementText.getPlacement()));
        buildElementWithValueAndAttributes(elementName, placementText.getValue(), attributes);
    }

    protected void buildLine(String elementName, Line line) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("line-shape", BuilderUtil.enumValue(line.getLineShape()));
        attributes.put("line-type", BuilderUtil.enumValue(line.getLineType()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(line.getDashedFormatting()));
        attributes.putAll(FormattingBuilder.buildPrintStyle(line.getPrintStyle()));
        attributes.put("placement", BuilderUtil.enumValue(line.getPlacement()));
        buildElementWithAttributes(elementName, attributes);
    }

    protected void buildStyleText(String elementName, StyleText styleText) {
        if (styleText ==  null) return;

        Map<String, String> attributes = new HashMap<>();
        attributes.putAll(FormattingBuilder.buildPrintStyle(styleText.getPrintStyle()));
        buildElementWithValueAndAttributes(elementName, styleText.getValue(), attributes);
    }

    protected void buildEditorial(Editorial editorial) {
        if (editorial == null) return;

        buildFormattedText("footnote", editorial.getFootnote());
        buildLevel(editorial.getLevel());
    }

    protected void buildEditorialVoice(EditorialVoice editorialVoice) {
        if (editorialVoice == null) return;

        buildFormattedText("footnote", editorialVoice.getFootnote());
        buildLevel(editorialVoice.getLevel());
        buildElementWithValue("voice", editorialVoice.getVoice());
    }

    protected void buildFormattedText(String elementName, FormattedText formattedText) {
        if (formattedText == null) return;

        buildElementWithValueAndAttributes(elementName, formattedText.getValue(), FormattingBuilder.buildTextFormatting(formattedText.getTextFormatting()));
    }

    protected void buildLevel(Level level) {
        if (level ==  null) return;

        Map<String, String> attributes = new HashMap<>();
        attributes.put("reference", BuilderUtil.yesOrNo(level.getReference()));
        attributes.putAll(FormattingBuilder.buildLevelDisplay(level.getLevelDisplay()));
        buildElementWithValueAndAttributes("level", level.getValue(), attributes);
    }

    protected void buildTuning(Tuning tuning) {
        if (tuning == null) return;

        buildElementWithValue("tuning-step", BuilderUtil.enumValue(tuning.getTuningStep()).toUpperCase());
        buildElementWithValue("tuning-alter", tuning.getTuningAlter());
        buildElementWithValue("tuning-octave", tuning.getTuningOctave());
    }

    protected void buildAccidentalText(AccidentalText accidentalText) {
        if (accidentalText == null) return;

        buildElementWithValueAndAttributes("accidental-text", BuilderUtil.enumValue(accidentalText.getAccidentalType()), FormattingBuilder.buildTextFormatting(accidentalText.getTextFormatting()));
    }

    protected void buildMidiDevice(MidiDevice midiDevice) {
        if (midiDevice == null) return;

        buildElementWithValueAndAttribute("midi-device", midiDevice.getValue(), "id", midiDevice.getMidiDeviceId());
    }
}
