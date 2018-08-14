package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.attributes.Tuning;
import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.common.Editorial;
import org.curtis.musicxml.common.EditorialVoice;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Level;
import org.curtis.musicxml.common.MidiDevice;
import org.curtis.musicxml.common.MidiInstrument;
import org.curtis.musicxml.common.StyleText;
import org.curtis.musicxml.common.play.Ipa;
import org.curtis.musicxml.common.play.Mute;
import org.curtis.musicxml.common.play.OtherPlay;
import org.curtis.musicxml.common.play.Play;
import org.curtis.musicxml.common.play.PlayType;
import org.curtis.musicxml.common.play.SemiPitched;
import org.curtis.musicxml.note.AccidentalText;
import org.curtis.musicxml.note.Line;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.PlacementText;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.lyric.Extend;
import org.curtis.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains methods to build musicxml for objects common to
 * more than one builder type
 */
public abstract class MusicDataBuilder extends BaseBuilder {
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

        buildElementWithValueAndAttributes("accidental-text", accidentalText.getAccidentalType(), FormattingBuilder.buildTextFormatting(accidentalText.getTextFormatting()));
    }

    protected void buildMidiDevice(MidiDevice midiDevice) {
        if (midiDevice == null) return;

        Map<String, String> attributes = new HashMap<>();
        attributes.put("port", BuilderUtil.stringValue(midiDevice.getPort()));
        attributes.put("id", midiDevice.getMidiDeviceId());
        buildElementWithValueAndAttributes("midi-device", midiDevice.getValue(), attributes);
    }

    protected void buildMidiInstrument(MidiInstrument midiInstrument) {
        if (midiInstrument == null) return;

        buildOpenElement("midi-instrument");
        buildAttribute("id", midiInstrument.getMidiInstrumentId());
        buildCloseElement();

        buildElementWithValue("midi-channel", midiInstrument.getMidiChannel());
        buildElementWithValue("midi-name", midiInstrument.getMidiName());
        buildElementWithValue("midi-bank", midiInstrument.getMidiBank());
        buildElementWithValue("midi-program", midiInstrument.getMidiProgram());
        buildElementWithValue("midi-unpitched", midiInstrument.getMidiUnpitched());
        buildElementWithValue("volume", midiInstrument.getVolume());
        buildElementWithValue("pan", midiInstrument.getPan());
        buildElementWithValue("elevation", midiInstrument.getElevation());

        buildEndElement("midi-instrument");
    }

    protected void buildPlay(Play play) {
        if (play == null) return;

        buildOpenElement("play");
        buildAttribute("id", play.getPlayId());
        buildCloseElement();

        for (PlayType playType : play.getPlayTypes()) {
            if (playType instanceof Ipa) {
                Ipa ipa = (Ipa)playType;
                buildElementWithValue("ipa", ipa.getValue());
            } else if (playType instanceof Mute) {
                Mute mute = (Mute)playType;
                buildElementWithValue("mute", mute.getMuteType());
            } else if (playType instanceof SemiPitched) {
                SemiPitched semiPitched = (SemiPitched)playType;
                buildElementWithValue("semi-pitched", semiPitched.getSemiPitchcedType());
            } else if (playType instanceof OtherPlay) {
                OtherPlay otherPlay = (OtherPlay)playType;
                buildElementWithValueAndAttribute("other-play", otherPlay.getValue(), "type", otherPlay.getType());
            }
        }

        buildEndElement("play");
    }
}
