package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.Directive;
import org.curtis.musicxml.attributes.key.Cancel;
import org.curtis.musicxml.attributes.key.Key;
import org.curtis.musicxml.attributes.key.KeyOctave;
import org.curtis.musicxml.attributes.key.NonTraditionalKey;
import org.curtis.musicxml.attributes.key.NonTraditionalKeyType;
import org.curtis.musicxml.attributes.key.TraditionalKey;
import org.curtis.musicxml.attributes.time.Interchangeable;
import org.curtis.musicxml.attributes.time.SenzaMisura;
import org.curtis.musicxml.attributes.time.Time;
import org.curtis.musicxml.attributes.time.TimeSignature;
import org.curtis.musicxml.attributes.time.TimeSignatureType;
import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.score.PartSymbol;

public class AttributesBuilder extends BaseBuilder {
    private Attributes attributes;

    public AttributesBuilder(Attributes attributes) {
        this.attributes = attributes;
    }

    public StringBuilder build() {
        appendLine("<attributes>");
        for (Key key : attributes.getKeys()) {
            appendLine("<key>");
            if (key instanceof TraditionalKey) buildTraditionalKey((TraditionalKey)key);
            else if (key instanceof NonTraditionalKey) buildNonTraditionalKey((NonTraditionalKey)key);
            for (KeyOctave keyOctave : key.getKeyOctaves()) {
                buildElementWithAttribute("key-octave", "number", keyOctave.getNumber());
            }
            appendLine("</key>");
        }
        buildElementWithValue("staves", attributes.getStaves());
        PartSymbol partSymbol = attributes.getPartSymbol();
        if (partSymbol != null) buildElementWithValue("part-symbol", BuilderUtil.enumValue(partSymbol.getGroupSymbolType()));
        buildElementWithValue("instruments", attributes.getInstruments());
        for (Directive directive : attributes.getDirectives()) {
            buildElementWithValue("directive", directive.getValue());
        }
        for (Time time : attributes.getTimeList()) {
            append("<time");
            buildAttribute("symbol", BuilderUtil.enumValue(time.getSymbol()));
            buildAttribute("separator", BuilderUtil.enumValue(time.getSeparator()));
            appendLine(">");
            if (time instanceof TimeSignature) buildTimeSignature((TimeSignature)time);
            else if (time instanceof SenzaMisura) buildSenzaMisura((SenzaMisura)time);
            appendLine("</time>");
        }
        appendLine("</attributes>");

        return stringBuilder;
    }

    private void buildTraditionalKey(TraditionalKey traditionalKey) {
        Cancel cancel = traditionalKey.getCancel();
        if (cancel != null) buildElementWithValueAndAttribute("cancel", cancel.getFifths(), "location", BuilderUtil.enumValue(cancel.getLocation()));
        buildElementWithValue("fifths", traditionalKey.getFifths());
        buildElementWithValue("mode", traditionalKey.getMode());
    }

    private void buildNonTraditionalKey(NonTraditionalKey nonTraditionalKey) {
        for (NonTraditionalKeyType nonTraditionalKeyType : nonTraditionalKey.getNonTraditionalKeyList()) {

        }
    }

    private void buildTimeSignature(TimeSignature timeSignature) {
        for (TimeSignatureType timeSignatureType : timeSignature.getTimeSignatureList()) {
            buildTimeSignatureType(timeSignatureType);
        }
        Interchangeable interchangeable = timeSignature.getInterchangeable();
        if (interchangeable != null) {
            append("<interchangeable");
            buildAttribute("symbol", BuilderUtil.enumValue(interchangeable.getSymbol()));
            buildAttribute("separator", BuilderUtil.enumValue(interchangeable.getSeparator()));
            appendLine(">");
            buildElementWithValue("time-relation", BuilderUtil.enumValue(interchangeable.getTimeRelation()));
            TimeSignature interchangeableTimeSignature = interchangeable.getTimeSignature();
            for (TimeSignatureType timeSignatureType : interchangeableTimeSignature.getTimeSignatureList()) {
                buildTimeSignatureType(timeSignatureType);
            }
            appendLine("</interchangeable>");
        }
    }

    private void buildSenzaMisura(SenzaMisura senzaMisura) {
        buildElementWithValue("senza-misura", senzaMisura.getValue());
    }

    private void buildTimeSignatureType(TimeSignatureType timeSignatureType) {
        buildElementWithValue("beats", timeSignatureType.getBeats());
        buildElementWithValue("beat-type", timeSignatureType.getBeatType());
    }
}
