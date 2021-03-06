package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.Clef;
import org.curtis.musicxml.attributes.Directive;
import org.curtis.musicxml.attributes.StaffDetails;
import org.curtis.musicxml.attributes.StaffTuning;
import org.curtis.musicxml.attributes.Transpose;
import org.curtis.musicxml.attributes.key.Key;
import org.curtis.musicxml.attributes.key.KeyOctave;
import org.curtis.musicxml.attributes.key.NonTraditionalKey;
import org.curtis.musicxml.attributes.key.NonTraditionalKeyType;
import org.curtis.musicxml.attributes.key.TraditionalKey;
import org.curtis.musicxml.attributes.measure.BeatRepeat;
import org.curtis.musicxml.attributes.measure.ExceptVoice;
import org.curtis.musicxml.attributes.measure.MeasureRepeat;
import org.curtis.musicxml.attributes.measure.MeasureStyle;
import org.curtis.musicxml.attributes.measure.MultipleRest;
import org.curtis.musicxml.attributes.measure.Slash;
import org.curtis.musicxml.attributes.measure.SlashGroup;
import org.curtis.musicxml.attributes.time.Interchangeable;
import org.curtis.musicxml.attributes.time.SenzaMisura;
import org.curtis.musicxml.attributes.time.Time;
import org.curtis.musicxml.attributes.time.TimeSignature;
import org.curtis.musicxml.attributes.time.TimeSignatureType;
import org.curtis.musicxml.builder.DisplayBuilder;
import org.curtis.musicxml.builder.BuilderUtil;
import org.curtis.musicxml.score.PartSymbol;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AttributesBuilder extends MusicDataBuilder {
    private Attributes attributes;

    public AttributesBuilder(Attributes attributes) {
        this.attributes = attributes;
    }

    public StringBuilder build() {
        buildStartElement("attributes");
        buildEditorial(attributes.getEditorial());
        BigDecimal divisions = attributes.getDivisions();
        if (divisions !=  null) buildElementWithValue("divisions", divisions);
        for (Key key : attributes.getKeys()) {
            buildOpenElement("key");
            buildAttribute("id", key.getElementId());
            buildAttribute("number", key.getNumber());
            buildAttributes(DisplayBuilder.buildDisplay(key.getDisplay()));
            buildAttribute("print-object", key.getPrintObject());
            buildCloseElement();
            if (key instanceof TraditionalKey) buildTraditionalKey((TraditionalKey)key);
            else if (key instanceof NonTraditionalKey) buildNonTraditionalKey((NonTraditionalKey)key);
            for (KeyOctave keyOctave : key.getKeyOctaves()) {
                Map<String, String> attributes = new HashMap<>();
                attributes.put("number", BuilderUtil.stringValue(keyOctave.getNumber()));
                attributes.put("cancel", BuilderUtil.yesOrNo(keyOctave.getCancel()));
                buildElementWithValueAndAttributes("key-octave", keyOctave.getOctave(), attributes);
            }
            buildEndElement("key");
        }
        for (Time time : attributes.getTimeList()) {
            buildOpenElement("time");
            buildAttribute("id", time.getElementId());
            buildAttribute("number", time.getNumber());
            buildAttribute("symbol", time.getSymbol());
            buildAttribute("separator", time.getSeparator());
            buildAttributes(DisplayBuilder.buildDisplay(time.getDisplay()));
            buildAttribute("print-object", time.getPrintObject());
            buildCloseElement();
            if (time instanceof TimeSignature) buildTimeSignature((TimeSignature)time);
            else if (time instanceof SenzaMisura) buildSenzaMisura((SenzaMisura)time);
            buildEndElement("time");
        }
        buildElementWithValue("staves", attributes.getStaves());
        PartSymbol partSymbol = attributes.getPartSymbol();
        if (partSymbol != null) {
            Map<String, String> partSymbolAttributes = new HashMap<>();
            partSymbolAttributes.put("top-staff", BuilderUtil.stringValue(partSymbol.getTopStaff()));
            partSymbolAttributes.put("bottom-staff", BuilderUtil.stringValue(partSymbol.getBottomStaff()));
            partSymbolAttributes.putAll(DisplayBuilder.buildDisplay(partSymbol.getDisplay()));
            buildElementWithValueAndAttributes("part-symbol", partSymbol.getValue(), partSymbolAttributes);
        }
        buildElementWithValue("instruments", attributes.getInstruments());
        for (Clef clef : attributes.getClefs()) {
            buildOpenElement("clef");
            buildAttribute("id", clef.getElementId());
            buildAttribute("number", clef.getNumber());
            buildAttribute("additional", clef.getAdditional());
            buildAttribute("after-barline", clef.getAfterBarline());
            buildAttribute("size", clef.getSize());
            buildAttributes(DisplayBuilder.buildDisplay(clef.getDisplay()));
            buildAttribute("print-object", clef.getPrintObject());
            buildCloseElement();
            String clefSign = BuilderUtil.enumValue(clef.getSign());
            switch (clefSign) {
                case "g":
                    clefSign = "G";
                    break;
                case "f":
                    clefSign = "F";
                    break;
                case "c":
                    clefSign = "C";
                    break;
                case "tab":
                    clefSign = "TAB";
                    break;
            }
            buildElementWithValue("sign", clefSign);
            buildElementWithValue("line", clef.getLine());
            buildElementWithValue("clef-octave-change", clef.getClefOctaveChange());
            buildEndElement("clef");
        }
        for (StaffDetails staffDetails : attributes.getStaffDetailsList()) {
            buildOpenElement("staff-details");
            buildAttribute("number", staffDetails.getNumber());
            buildAttribute("show-frets", staffDetails.getShowFrets());
            buildAttribute("print-object", staffDetails.getPrintObject());
            buildAttribute("print-spacing", staffDetails.getPrintSpacing());
            buildCloseElement();
            buildElementWithValue("staff-type", staffDetails.getStaffType());
            buildElementWithValue("staff-lines", staffDetails.getStaffLines());
            for (StaffTuning staffTuning : staffDetails.getStaffTunings()) {
                buildOpenElement("staff-tuning");
                buildAttribute("line", staffTuning.getLine());
                buildCloseElement();
                buildTuning(staffTuning.getTuning());
                buildEndElement("staff-tuning");
            }
            buildElementWithValue("capo", staffDetails.getCapo());
            buildElementWithValue("staff-size", staffDetails.getStaffSize());
            buildEndElement("staff-details");
        }
        for (Transpose transpose : attributes.getTranspositions()) {
            buildOpenElement("transpose");
            buildAttribute("id", transpose.getElementId());
            buildAttribute("number", transpose.getNumber());
            buildCloseElement();
            buildElementWithValue("diatonic", transpose.getDiatonic());
            buildElementWithValue("chromatic", transpose.getChromatic());
            buildElementWithValue("octave-change", transpose.getOctaveChange());
            if (transpose.getDoubled()) buildElement("double");
            buildEndElement("transpose");
        }
        for (Directive directive : attributes.getDirectives()) {
            Map<String, String> directiveAttributes = new HashMap<>(DisplayBuilder.buildDisplay(directive.getDisplay()));
            directiveAttributes.put("xml:lang", directive.getLang());
            buildElementWithValueAndAttributes("directive", directive.getValue(), directiveAttributes);
        }
        for (MeasureStyle measureStyle : attributes.getMeasureStyles()) {
            buildOpenElement("measure-style");
            buildAttribute("id", measureStyle.getElementId());
            buildAttribute("number", measureStyle.getNumber());
            buildAttributes(DisplayBuilder.buildDisplay(measureStyle.getDisplay()));
            buildCloseElement();
            if (measureStyle instanceof MultipleRest) {
                MultipleRest multipleRest = (MultipleRest)measureStyle;
                buildElementWithValueAndAttribute("multiple-rest", multipleRest.getValue(), "use-symbols", multipleRest.getUseSymbols());
            }
            else if (measureStyle instanceof MeasureRepeat) {
                MeasureRepeat measureRepeat = (MeasureRepeat)measureStyle;
                Map<String, String> attributes = new HashMap<>();
                attributes.put("type", BuilderUtil.enumValue(measureRepeat.getType()));
                attributes.put("slashes", BuilderUtil.stringValue(measureRepeat.getSlashes()));
                buildElementWithValueAndAttributes("measure-repeat", measureRepeat.getValue(), attributes);
            }
            else if (measureStyle instanceof BeatRepeat) {
                BeatRepeat beatRepeat = (BeatRepeat)measureStyle;
                buildOpenElement("beat-repeat");
                buildAttribute("type", BuilderUtil.enumValue(beatRepeat.getType()));
                buildAttribute("slashes", beatRepeat.getSlashes());
                buildAttribute("use-dots", beatRepeat.getUseDots());
                buildCloseElement();
                buildSlashGroup(beatRepeat.getSlashGroup());
                buildEndElement("beat-repeat");
            }
            else if (measureStyle instanceof Slash) {
                Slash slash = (Slash)measureStyle;
                buildOpenElement("slash");
                buildAttribute("type", BuilderUtil.enumValue(slash.getType()));
                buildAttribute("use-dots", slash.getUseDots());
                buildAttribute("use-stems", slash.getUseStems());
                buildCloseElement();
                buildSlashGroup(slash.getSlashGroup());
                buildEndElement("slash");
            }
            buildEndElement("measure-style");
        }
        buildEndElement("attributes");

        return stringBuilder;
    }

    private void buildTraditionalKey(TraditionalKey traditionalKey) {
        if (traditionalKey.hasCancel()) buildElementWithValueAndAttribute("cancel", traditionalKey.getCancelFifths(), "location", traditionalKey.getCancelLocation());
        buildElementWithValue("fifths", traditionalKey.getFifths());
        buildElementWithValue("mode", traditionalKey.getMode());
    }

    private void buildNonTraditionalKey(NonTraditionalKey nonTraditionalKey) {
        for (NonTraditionalKeyType nonTraditionalKeyType : nonTraditionalKey.getNonTraditionalKeyList()) {
            buildElementWithValue("key-step", BuilderUtil.enumValue(nonTraditionalKeyType.getKeyStep()).toUpperCase());
            buildElementWithValue("key-alter", nonTraditionalKeyType.getKeyAlter());
            buildElementWithValueAndAttribute("key-accidental", nonTraditionalKeyType.getKeyAccidental(), "smufl", nonTraditionalKeyType.getKeyAccidentalSmufl());
        }
    }

    private void buildTimeSignature(TimeSignature timeSignature) {
        for (TimeSignatureType timeSignatureType : timeSignature.getTimeSignatureList()) buildTimeSignatureType(timeSignatureType);
        Interchangeable interchangeable = timeSignature.getInterchangeable();
        if (interchangeable != null) {
            buildOpenElement("interchangeable");
            buildAttribute("symbol", interchangeable.getSymbol());
            buildAttribute("separator", interchangeable.getSeparator());
            buildCloseElement();
            buildElementWithValue("time-relation", interchangeable.getTimeRelation());
            for (TimeSignatureType timeSignatureType : interchangeable.getTimeSignatureList()) buildTimeSignatureType(timeSignatureType);
            buildEndElement("interchangeable");
        }
    }

    private void buildSenzaMisura(SenzaMisura senzaMisura) {
        buildElementWithValue("senza-misura", senzaMisura.getValue());
    }

    private void buildTimeSignatureType(TimeSignatureType timeSignatureType) {
        buildElementWithValue("beats", timeSignatureType.getBeats());
        buildElementWithValue("beat-type", timeSignatureType.getBeatType());
    }

    private void buildSlashGroup(SlashGroup slashGroup) {
        if (slashGroup == null) return;

        buildElementWithValue("slash-type", BuilderUtil.noteTypeValue(slashGroup.getSlashType()));
        for (int dotCount = 1; dotCount <= slashGroup.getSlashDots(); dotCount++) buildElement("slash-dot");
        for (ExceptVoice exceptVoice : slashGroup.getExceptVoices()) buildElementWithValue("except-voice", exceptVoice.getValue());
    }
}
