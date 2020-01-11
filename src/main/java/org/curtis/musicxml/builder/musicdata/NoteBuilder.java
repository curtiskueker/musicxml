package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.FormattingBuilder;
import org.curtis.musicxml.builder.PlacementBuilder;
import org.curtis.musicxml.builder.BuilderUtil;
import org.curtis.musicxml.common.Text;
import org.curtis.musicxml.note.Accidental;
import org.curtis.musicxml.note.Beam;
import org.curtis.musicxml.note.BeamType;
import org.curtis.musicxml.note.Dot;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.FullNoteType;
import org.curtis.musicxml.note.Grace;
import org.curtis.musicxml.note.Notations;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.NoteType;
import org.curtis.musicxml.note.Notehead;
import org.curtis.musicxml.note.NoteheadText;
import org.curtis.musicxml.note.Pitch;
import org.curtis.musicxml.note.Rest;
import org.curtis.musicxml.note.Stem;
import org.curtis.musicxml.note.Tie;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.Unpitched;
import org.curtis.musicxml.note.lyric.Extend;
import org.curtis.musicxml.note.lyric.Humming;
import org.curtis.musicxml.note.lyric.Laughing;
import org.curtis.musicxml.note.lyric.Lyric;
import org.curtis.musicxml.note.lyric.LyricItem;
import org.curtis.musicxml.note.lyric.LyricSyllable;
import org.curtis.musicxml.note.lyric.LyricText;
import org.curtis.musicxml.note.lyric.TextData;
import org.curtis.musicxml.note.lyric.TextFontColor;
import org.curtis.musicxml.note.notation.Notation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteBuilder extends MusicDataBuilder {
    private Note note;

    public NoteBuilder(Note note) {
        this.note = note;
    }

    public StringBuilder build() {
        if (note == null) return stringBuilder;

        buildOpenElement("note");
        buildAttributes(PlacementBuilder.buildPosition(note.getPosition()));
        buildAttributes(FormattingBuilder.buildFont(note.getFont()));
        buildAttribute("color", note.getColor());
        buildAttributes(FormattingBuilder.buildPrintout(note.getPrintout()));
        buildAttribute("dynamics", note.getDynamics());
        buildAttribute("end-dynamics", note.getEndDynamics());
        buildAttribute("attack", note.getAttack());
        buildAttribute("release", note.getRelease());
        buildAttribute("time-only", note.getTimeOnly());
        buildAttribute("pizzicato",  note.getPizzicato());
        buildCloseElement();
        if (note.getCue()) buildElement("cue");
        Grace grace = note.getGrace();
        if (grace != null) {
            Map<String, String> graceAttributes = new HashMap<>();
            graceAttributes.put("steal-time-previous", BuilderUtil.stringValue(grace.getStealTimePrevious()));
            graceAttributes.put("steal-time-following", BuilderUtil.stringValue(grace.getStealTimeFollowing()));
            graceAttributes.put("make-time", BuilderUtil.stringValue(grace.getMakeTime()));
            graceAttributes.put("slash", BuilderUtil.yesOrNo(grace.getSlash()));
            buildElementWithAttributes("grace", graceAttributes);
        }
        buildFullNote(note.getFullNote());
        if (grace == null) buildElementWithValue("duration", note.getDuration());
        for (Tie tie : note.getTies()) {
            Map<String, String> tieAttributes = new HashMap<>();
            tieAttributes.put("type", BuilderUtil.enumValue(tie.getType()));
            tieAttributes.put("time-only", tie.getTimeOnly());
            buildElementWithAttributes("tie", tieAttributes);
        }
        buildElementWithAttribute("instrument", "id", note.getInstrument());
        buildEditorialVoice(note.getEditorialVoice());
        NoteType noteType = note.getType();
        if (noteType != null) {
            buildElementWithValueAndAttribute("type", BuilderUtil.noteTypeValue(noteType.getValue()), "size", noteType.getSize());
        }
        for (Dot dot : note.getDots()) {
            buildPlacement("dot", dot.getPrintPlacement());
        }
        Accidental accidental = note.getAccidental();
        if (accidental != null) {
            Map<String, String> accidentalAttributes = new HashMap<>();
            accidentalAttributes.put("cautionary", BuilderUtil.yesOrNo(accidental.getCautionary()));
            accidentalAttributes.put("editorial", BuilderUtil.yesOrNo(accidental.getCautionary()));
            accidentalAttributes.putAll(FormattingBuilder.buildLevelDisplay(accidental.getLevelDisplay()));
            accidentalAttributes.putAll(FormattingBuilder.buildPrintStyle(accidental.getPrintStyle()));
            buildElementWithValueAndAttributes("accidental", accidental.getAccidentalType(), accidentalAttributes);
        }
        TimeModification timeModification = note.getTimeModification();
        if (timeModification != null) {
            buildStartElement("time-modification");
            buildTimeModification(note.getTimeModification());
            buildEndElement("time-modification");
        }
        Stem stem = note.getStem();
        if (stem != null) {
            Map<String, String> stemAttributes = new HashMap<>();
            stemAttributes.putAll(PlacementBuilder.buildPosition(stem.getPosition()));
            stemAttributes.put("color", stem.getColor());
            buildElementWithValueAndAttributes("stem", stem.getType(), stemAttributes);
        }
        Notehead notehead = note.getNotehead();
        if (notehead != null) {
            String noteheadType = BuilderUtil.enumValueWithSpaces(notehead.getType());
            noteheadType = noteheadType.replace("circle x", "circle-x");
            Map<String, String> noteheadAttributes = new HashMap<>();
            noteheadAttributes.put("filled", BuilderUtil.yesOrNo(notehead.getFilled()));
            noteheadAttributes.put("parentheses", BuilderUtil.yesOrNo(notehead.getParentheses()));
            noteheadAttributes.putAll(FormattingBuilder.buildFont(notehead.getFont()));
            noteheadAttributes.put("color", notehead.getColor());
            buildElementWithValueAndAttributes("notehead", noteheadType, noteheadAttributes);
        }
        NoteheadText noteheadText = note.getNoteheadText();
        if (noteheadText != null) {
            List<Text> textList = noteheadText.getTextList();
            if (!textList.isEmpty()) {
                buildStartElement("notehead-text");
                for (Text text : textList) {
                    buildText(text);
                }
                buildEndElement("notehead_text");
            }
        }
        buildElementWithValue("staff", note.getStaff());
        List<Beam> beams = note.getBeams();
        if (!beams.isEmpty()) {
            for (Beam beam : beams) {
                Map<String, String> beamAttributes = new HashMap<>();
                beamAttributes.put("number", BuilderUtil.stringValue(beam.getNumber()));
                beamAttributes.put("repeater", BuilderUtil.yesOrNo(beam.getRepeater()));
                beamAttributes.put("fan", BuilderUtil.enumValue(beam.getFan()));
                beamAttributes.put("color", beam.getColor());
                buildElementWithValueAndAttributes("beam", buildBeamType(beam.getType()), beamAttributes);
            }
        }
        List<Notations> notationsList = note.getNotationsList();
        if (!notationsList.isEmpty()) {
            for (Notations notations : notationsList) buildNotations(notations);
        }
        for (Lyric lyric : note.getLyrics()) buildLyric(lyric);
        buildPlay(note.getPlay());
        buildEndElement("note");

        return stringBuilder;
    }

    private void buildFullNote(FullNote fullNote) {
        if (fullNote == null) return;

        if (fullNote.isChord()) buildElement("chord");
        FullNoteType fullNoteType = fullNote.getFullNoteType();
        if (fullNoteType instanceof Pitch) buildPitch((Pitch)fullNoteType);
        else if (fullNoteType instanceof Unpitched) buildUnpitched((Unpitched)fullNoteType);
        else if (fullNoteType instanceof Rest) buildRest((Rest)fullNoteType);
    }

    private void buildPitch(Pitch pitch) {
        buildStartElement("pitch");
        buildElementWithValue("step", BuilderUtil.enumValue(pitch.getStep()).toUpperCase());
        buildElementWithValue("alter", pitch.getAlter());
        buildElementWithValue("octave", pitch.getOctave());
        buildEndElement("pitch");
    }

    private void buildUnpitched(Unpitched unpitched) {
        buildStartElement("unpitched");
        buildElementWithValue("display-step", BuilderUtil.enumValue(unpitched.getDisplayStep()).toUpperCase());
        buildElementWithValue("display-octave", unpitched.getDisplayOctave());
        buildEndElement("unpitched");
    }

    private void buildRest(Rest rest) {
        buildOpenElement("rest");
        buildAttribute("measure",  rest.getMeasure());
        buildCloseElement();
        buildElementWithValue("display-step", BuilderUtil.enumValue(rest.getDisplayStep()).toUpperCase());
        buildElementWithValue("display-octave", rest.getDisplayOctave());
        buildEndElement("rest");
    }

    public static String buildBeamType(BeamType beamType) {
        return BuilderUtil.enumValueWithSpaces(beamType);
    }

    private void buildNotations(Notations notations) {
        buildOpenElement("notations");
        buildAttribute("print-object",  notations.getPrintObject());
        buildCloseElement();
        buildEditorial(notations.getEditorial());
        for (Notation notation : notations.getNotations()) {
            NotationBuilder notationBuilder = new NotationBuilder(notation);
            append(notationBuilder.build().toString());
        }
        buildEndElement("notations");
    }

    private void buildLyric(Lyric lyric) {
        buildOpenElement("lyric");
        buildAttribute("number", lyric.getNumber());
        buildAttribute("name", lyric.getName());
        buildAttribute("justify", lyric.getJustify());
        buildAttributes(PlacementBuilder.buildPosition(lyric.getPosition()));
        buildAttribute("placement", lyric.getPlacement());
        buildAttribute("color", lyric.getColor());
        buildAttribute("print-object",  lyric.getPrintObject());
        buildCloseElement();
        LyricItem lyricItem = lyric.getLyricItem();
        if (lyricItem instanceof LyricText) {
            LyricText lyricText = (LyricText)lyricItem;
            for (LyricSyllable lyricSyllable : lyricText.getLyricSyllables()) {
                TextFontColor lyricElision = lyricSyllable.getLyricElision();
                if (lyricElision != null) {
                    Map<String, String> elisionAttributes = new HashMap<>();
                    elisionAttributes.putAll(FormattingBuilder.buildFont(lyricElision.getFont()));
                    elisionAttributes.put("color", lyricElision.getColor());
                    elisionAttributes.putAll(FormattingBuilder.buildTextDecoration(lyricElision.getTextDecoration()));
                    elisionAttributes.put("rotation", BuilderUtil.stringValue(lyricElision.getTextRotation()));
                    elisionAttributes.put("letter-spacing", lyricElision.getLetterSpacing());
                    elisionAttributes.put("xml:lang", lyricElision.getLang());
                    elisionAttributes.put("dir", BuilderUtil.enumValue(lyricElision.getTextDirection()));
                    buildElementWithValueAndAttributes("elision", lyricElision.getValue(), elisionAttributes);
                }
                buildElementWithValue("syllabic", lyricSyllable.getSyllabic());
                TextData lyricSyllableText = lyricSyllable.getText();
                Map<String, String> textAttributes = new HashMap<>();
                textAttributes.putAll(FormattingBuilder.buildFont(lyricSyllableText.getFont()));
                textAttributes.put("color", lyricSyllableText.getColor());
                textAttributes.putAll(FormattingBuilder.buildTextDecoration(lyricSyllableText.getTextDecoration()));
                textAttributes.put("rotation", BuilderUtil.stringValue(lyricSyllableText.getTextRotation()));
                textAttributes.put("letter-spacing", lyricSyllableText.getLetterSpacing());
                textAttributes.put("xml:lang", lyricSyllableText.getLang());
                textAttributes.put("dir", BuilderUtil.enumValue(lyricSyllableText.getTextDirection()));
                buildElementWithValueAndAttributes("text", lyricSyllableText.getValue(), textAttributes);
            }
            buildExtend(lyricText.getExtend());
        }
        else if (lyricItem instanceof Extend) buildExtend((Extend)lyricItem);
        else if (lyricItem instanceof Laughing) buildElement("laughing");
        else if (lyricItem instanceof Humming) buildElement("humming");
        if (lyric.getEndLine()) buildElement("end-line");
        if (lyric.getEndParagraph()) buildElement("end-paragraph");
        buildEditorial(lyric.getEditorial());
        buildEndElement("lyric");
    }
}
