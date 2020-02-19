package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.attributes.Image;
import org.curtis.musicxml.attributes.Tuning;
import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.DisplayBuilder;
import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.builder.BuilderUtil;
import org.curtis.musicxml.common.DisplayText;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.display.Editorial;
import org.curtis.musicxml.common.Level;
import org.curtis.musicxml.common.MidiDevice;
import org.curtis.musicxml.common.MidiInstrument;
import org.curtis.musicxml.common.NameDisplay;
import org.curtis.musicxml.common.StyleText;
import org.curtis.musicxml.common.TextDisplay;
import org.curtis.musicxml.common.play.Ipa;
import org.curtis.musicxml.common.play.Mute;
import org.curtis.musicxml.common.play.OtherPlay;
import org.curtis.musicxml.common.play.Play;
import org.curtis.musicxml.common.play.PlayType;
import org.curtis.musicxml.common.play.SemiPitched;
import org.curtis.musicxml.direction.directiontype.Coda;
import org.curtis.musicxml.direction.directiontype.Dynamics;
import org.curtis.musicxml.direction.directiontype.DynamicsMarking;
import org.curtis.musicxml.direction.directiontype.Segno;
import org.curtis.musicxml.display.FormattedDisplay;
import org.curtis.musicxml.display.TextFormat;
import org.curtis.musicxml.note.AccidentalText;
import org.curtis.musicxml.note.Line;
import org.curtis.musicxml.note.PlacementText;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.lyric.Extend;
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.ornament.TrillSound;
import org.curtis.musicxml.note.notation.ornament.WavyLine;
import org.curtis.musicxml.note.notation.technical.Fingering;
import org.curtis.musicxml.note.notation.technical.Fret;
import org.curtis.musicxml.note.notation.technical.StringNumber;

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
        attributes.putAll(DisplayBuilder.buildDisplay(extend.getDisplay()));
        buildElementWithAttributes("extend", attributes);
    }

    protected void buildOtherPlacementText(String elementName, PlacementText placementText, String smufl) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(placementText.getDisplay()));
        attributes.put("smufl", smufl);
        buildElementWithValueAndAttributes(elementName, placementText.getValue(), attributes);
    }

    protected void buildLine(String elementName, Line line) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("line-shape", BuilderUtil.enumValue(line.getLineShape()));
        attributes.put("line-type", BuilderUtil.enumValue(line.getLineType()));
        attributes.put("line-length", BuilderUtil.enumValue(line.getLineLength()));
        attributes.putAll(FormattingBuilder.buildDashedFormatting(line.getDashedFormatting()));
        attributes.putAll(DisplayBuilder.buildDisplay(line.getDisplay()));
        buildElementWithAttributes(elementName, attributes);
    }

    protected void buildStyleText(String elementName, StyleText styleText) {
        if (styleText ==  null) return;

        buildElementWithValueAndAttributes(elementName, styleText.getValue(), DisplayBuilder.buildDisplay(styleText.getDisplay()));
    }

    protected void buildEditorial(Editorial editorial) {
        if (editorial == null) return;

        buildFormattedDisplay("footnote", editorial.getFootnote());
        buildLevel(editorial.getLevel());
        buildElementWithValue("voice", editorial.getVoice());
    }

    protected void buildFormattedDisplay(String elementName, FormattedDisplay formattedDisplay) {
        if (formattedDisplay == null) return;

        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(formattedDisplay.getDisplay()));
        TextFormat textFormat = formattedDisplay.getTextFormat();
        if (textFormat != null) {
            attributes.putAll(FormattingBuilder.buildTextFormat(textFormat));
            buildElementWithValueAndAttributes(elementName, textFormat.getValue(), attributes);
        } else buildElementWithAttributes(elementName, attributes);
    }

    protected void buildFormattedDisplay(String elementName, Display display, TextFormat textFormat) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(display));
        if (textFormat != null) {
            attributes.putAll(FormattingBuilder.buildTextFormat(textFormat));
            buildElementWithValueAndAttributes(elementName, textFormat.getValue(), attributes);
        } else buildElementWithAttributes(elementName, attributes);
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

        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(accidentalText.getDisplay()));
        attributes.putAll(FormattingBuilder.buildTextFormat(accidentalText.getTextFormat()));
        attributes.put("smufl", accidentalText.getSmufl());
        buildElementWithValueAndAttributes("accidental-text", accidentalText.getAccidentalType(), attributes);
    }

    protected void buildText(TextDisplay textDisplay) {
        if (textDisplay == null) return;

        if (textDisplay instanceof DisplayText) {
            buildFormattedDisplay("display-text", textDisplay);
        } else if (textDisplay instanceof AccidentalText) {
            buildAccidentalText(((AccidentalText)textDisplay));
        }
    }

    protected void buildNameDisplay(String elementName, NameDisplay nameDisplay) {
        if (nameDisplay == null) return;

        buildOpenElement(elementName);
        buildAttribute("print-object", nameDisplay.getPrintObject());
        buildCloseElement();

        for (TextDisplay text : nameDisplay.getTextList()) buildText(text);

        buildEndElement(elementName);
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
                buildElementWithValue("semi-pitched", semiPitched.getSemiPitchedType());
            } else if (playType instanceof OtherPlay) {
                OtherPlay otherPlay = (OtherPlay)playType;
                buildElementWithValueAndAttribute("other-play", otherPlay.getValue(), "type", BuilderUtil.requiredValue(otherPlay.getType()));
            }
        }

        buildEndElement("play");
    }

    protected void buildImage(Image image) {
        if (image == null) return;

        Map<String, String> attributes = new HashMap<>();
        attributes.put("source", BuilderUtil.requiredValue(image.getSource()));
        attributes.put("type", BuilderUtil.requiredValue(image.getType()));
        attributes.put("height", BuilderUtil.stringValue(image.getHeight()));
        attributes.put("width", BuilderUtil.stringValue(image.getWidth()));
        attributes.putAll(DisplayBuilder.buildDisplay(image.getDisplay()));
        buildElementWithAttributes("image", attributes);
    }

    protected void buildWavyLine(WavyLine wavyLine) {
        if (wavyLine == null) return;

        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(wavyLine.getType()));
        attributes.put("number", BuilderUtil.stringValue(wavyLine.getNumber()));
        attributes.putAll(DisplayBuilder.buildDisplay(wavyLine.getDisplay()));
        attributes.putAll(buildTrillSound(wavyLine.getTrillSound()));
        buildElementWithAttributes("wavy-line", attributes);
    }

    protected static Map<String, String> buildTrillSound(TrillSound trillSound) {
        Map<String, String> attributes = new HashMap<>();
        if (trillSound == null) return attributes;

        attributes.put("start-note", BuilderUtil.enumValue(trillSound.getStartNote()));
        attributes.put("trill-step", BuilderUtil.enumValue(trillSound.getTrillStep()));
        attributes.put("two-note-turn", BuilderUtil.enumValue(trillSound.getTwoNoteTurn()));
        attributes.put("accelerate", BuilderUtil.yesOrNo(trillSound.getAccelerate()));
        attributes.put("beats", BuilderUtil.stringValue(trillSound.getBeats()));
        attributes.put("second-beat", BuilderUtil.stringValue(trillSound.getSecondBeat()));
        attributes.put("last-beat", BuilderUtil.stringValue(trillSound.getLastBeat()));

        return attributes;
    }

    protected void buildFermata(Fermata fermata) {
        if (fermata == null) return;

        Map<String, String> attributes = new HashMap<>();
        attributes.put("type", BuilderUtil.enumValue(fermata.getType()));
        attributes.putAll(DisplayBuilder.buildDisplay(fermata.getDisplay()));
        buildElementWithValueAndAttributes("fermata", fermata.getFermataShape(), attributes);
    }

    protected void buildDynamics(Dynamics dynamics) {
        if (dynamics == null) return;

        buildOpenElement("dynamics");
        buildAttributes(DisplayBuilder.buildDisplay(dynamics.getDisplay()));
        buildAttributes(FormattingBuilder.buildTextFormat(dynamics.getTextFormat()));
        buildCloseElement();
        for (DynamicsMarking dynamicsMarking : dynamics.getMarkings()) {
            buildElementWithValueAndAttribute(BuilderUtil.enumValue(dynamicsMarking.getDynamicsType()), dynamicsMarking.getValue(), "smufl", dynamicsMarking.getSmufl());
        }
        buildEndElement("dynamics");
    }

    protected void buildCoda(Coda coda) {
        if (coda == null) return;

        Map<String, String> attributes = DisplayBuilder.buildDisplay(coda.getDisplay());
        attributes.put("smufl", coda.getSmufl());
        buildElementWithOptionalAttributes("coda", attributes);
    }

    protected void buildSegno(Segno segno) {
        if (segno == null) return;

        Map<String, String> attributes = DisplayBuilder.buildDisplay(segno.getDisplay());
        attributes.put("smufl", segno.getSmufl());
        buildElementWithOptionalAttributes("segno", attributes);
    }

    protected void buildStringNumber(StringNumber stringNumber) {
        Map<String, String> attributes = new HashMap<>(DisplayBuilder.buildDisplay(stringNumber.getDisplay()));
        buildElementWithValueAndAttributes("string", stringNumber.getStringNumber(), attributes);
    }

    protected void buildFret(Fret fret) {
        buildElementWithValueAndAttributes("fret", fret.getValue(), DisplayBuilder.buildDisplay(fret.getDisplay()));
    }

    protected void buildFingering(Fingering fingering) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("substitution", BuilderUtil.yesOrNo(fingering.getSubstitution()));
        attributes.put("alternate", BuilderUtil.yesOrNo(fingering.getAlternate()));
        attributes.putAll(DisplayBuilder.buildDisplay(fingering.getDisplay()));
        buildElementWithValueAndAttributes("fingering", fingering.getValue(), attributes);
    }
}
