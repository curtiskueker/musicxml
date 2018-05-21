package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.PlacementBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.note.Accidental;
import org.curtis.musicxml.note.Beam;
import org.curtis.musicxml.note.BeamType;
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
import org.curtis.musicxml.note.XPosition;
import org.curtis.musicxml.note.YPosition;
import org.curtis.musicxml.note.lyric.Extend;
import org.curtis.musicxml.note.lyric.Lyric;
import org.curtis.musicxml.note.lyric.LyricItem;
import org.curtis.musicxml.note.lyric.LyricSyllable;
import org.curtis.musicxml.note.lyric.LyricText;
import org.curtis.musicxml.note.lyric.TextData;
import org.curtis.musicxml.note.lyric.TextFontColor;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteBuilder extends BaseBuilder {
    private Note note;

    public NoteBuilder(Note note) {
        this.note = note;
    }

    public StringBuilder build() {
        if (note == null) return stringBuilder;

        append("<note");
        XPosition xPosition = note.getxPosition();
        if (xPosition != null) {
            buildAttribute("default-x", BuilderUtil.stringValue(xPosition.getDefaultX()));
            buildAttribute("default-y", BuilderUtil.stringValue(xPosition.getDefaultY()));
            buildAttribute("relative-x", BuilderUtil.stringValue(xPosition.getRelativeX()));
            buildAttribute("relative-y", BuilderUtil.stringValue(xPosition.getRelativeY()));
        }
        buildAttribute("pizzicato", BuilderUtil.yesOrNo(note.getPizzicato()));
        appendLine(">");
        Grace grace = note.getGrace();
        if (grace != null) buildElementWithAttribute("grace", "slash", BuilderUtil.yesOrNo(grace.getSlash()));
        buildFullNote(note.getFullNote());
        // TODO: duration
        if (grace == null) buildElementWithValue("duration", 1);
        for (Tie tie : note.getTies()) buildElementWithAttribute("tie", "type", BuilderUtil.enumValue(tie.getType()));
        String instrument = note.getInstrument();
        if (StringUtil.isNotEmpty(instrument)) buildElementWithAttribute("instrument", "id", instrument);
        NoteType noteType = note.getType();
        if (noteType != null) {
            buildElementWithValueAndAttribute("type", BuilderUtil.noteTypeValue(noteType.getValue()), "size", BuilderUtil.enumValue(noteType.getSize()));
        }
        // TODO: note dot Placement
        for (Integer dotCount = 1; dotCount <= note.getDots(); dotCount++) {
            buildElement("dot");
        }
        Accidental accidental = note.getAccidental();
        // TODO: accidental-value
        if (accidental != null) {
            Map<String, String> accidentalAttributes = new HashMap<>();
            accidentalAttributes.put("cautionary", BuilderUtil.yesOrNo(accidental.getCautionary()));
            accidentalAttributes.put("editorial", BuilderUtil.yesOrNo(accidental.getCautionary()));
            buildElementWithValueAndAttributes("accidental", "sharp", accidentalAttributes);
        }
        TimeModification timeModification = note.getTimeModification();
        if (timeModification != null) {
            appendLine("<time-modification>");
            buildTimeModification(note.getTimeModification());
            appendLine("</time-modification>");
        }
        Stem stem = note.getStem();
        if (stem != null) {
            Map<String, String> attributes = new HashMap<>();
            YPosition yPosition = stem.getyPosition();
            if (yPosition != null) {
                attributes.put("default-x", BuilderUtil.stringValue(yPosition.getDefaultX()));
                attributes.put("default-y", BuilderUtil.stringValue(yPosition.getDefaultY()));
                attributes.put("relative-x", BuilderUtil.stringValue(yPosition.getRelativeX()));
                attributes.put("relative-y", BuilderUtil.stringValue(yPosition.getRelativeY()));
            }
            buildElementWithValueAndAttributes("stem", BuilderUtil.enumValue(stem.getType()), attributes);
        }
        Notehead notehead = note.getNotehead();
        if (notehead != null) {
            String noteheadType = BuilderUtil.enumValue(notehead.getType());
            noteheadType = noteheadType.replace("inverted-triangle", "inverted triangle");
            noteheadType = noteheadType.replace("arrow-down", "arrow down");
            noteheadType = noteheadType.replace("arrow-up", "arrow up");
            noteheadType = noteheadType.replace("back-slashed", "back slashed");
            noteheadType = noteheadType.replace("circle-dot", "circle dot");
            noteheadType = noteheadType.replace("left-triangle", "left triangle");
            noteheadType = noteheadType.replace("fa-up", "fa up");
            Map<String, String> noteheadAttributes = new HashMap<>();
            noteheadAttributes.put("filled", BuilderUtil.yesOrNo(notehead.getFilled()));
            noteheadAttributes.put("parentheses", BuilderUtil.yesOrNo(notehead.getParentheses()));
            buildElementWithValueAndAttributes("notehead", noteheadType, noteheadAttributes);
        }
        List<NoteheadText> noteheadTextList = note.getNoteheadTextList();
        if (!noteheadTextList.isEmpty()) buildElement("notehead-text");
        List<Beam> beams = note.getBeams();
        if (!beams.isEmpty()) {
            for (Beam beam : beams) {
                Map<String, String> attributes = new HashMap<>();
                attributes.put("number", BuilderUtil.stringValue(beam.getNumber()));
                attributes.put("repeater", BuilderUtil.yesOrNo(beam.getRepeater()));
                attributes.put("fan", BuilderUtil.enumValue(beam.getFan()));
                buildElementWithValueAndAttributes("beam", buildBeamType(beam.getType()), attributes);
            }
        }
        List<Notations> notationsList = note.getNotationsList();
        if (!notationsList.isEmpty()) {
            for (Notations notations : notationsList) buildNotations(notations);
        }
        for (Lyric lyric : note.getLyrics()) buildLyric(lyric);
        appendLine("</note>");

        return stringBuilder;
    }

    private void buildFullNote(FullNote fullNote) {
        if (fullNote == null) return;

        FullNoteType fullNoteType = fullNote.getFullNoteType();
        if (fullNoteType instanceof Pitch) buildPitch((Pitch)fullNoteType);
        else if (fullNoteType instanceof Unpitched) buildUnpitched((Unpitched)fullNoteType);
        else if (fullNoteType instanceof Rest) buildRest((Rest)fullNoteType);
    }

    private void buildPitch(Pitch pitch) {
        appendLine("<pitch>");
        // TODO: pitch values
        buildElementWithValue("step", "A");
        buildElementWithValue("octave", 0);
        appendLine("</pitch>");
    }

    private void buildUnpitched(Unpitched unpitched) {
        buildElement("unpitched");
    }

    private void buildRest(Rest rest) {
        buildElementWithAttribute("rest", "measure", BuilderUtil.yesOrNo(rest.getMeasure()));
    }

    public static String buildBeamType(BeamType beamType) {
        return BuilderUtil.enumValueWithSpaces(beamType);
    }

    private void buildNotations(Notations notations) {
        appendLine("<notations>");
        for (Notation notation : notations.getNotations()) {
            NotationBuilder notationBuilder = new NotationBuilder(notation);
            append(notationBuilder.build().toString());
        }
        appendLine("</notations>");
    }

    private void buildLyric(Lyric lyric) {
        append("<lyric");
        buildAttribute("number", lyric.getNumber());
        buildAttribute("name", lyric.getName());
        PlacementBuilder.buildPosition(lyric.getPosition()).forEach((k, v) -> buildAttribute(k, v));
        buildAttribute("placement", BuilderUtil.enumValue(lyric.getPlacement()));
        appendLine(">");
        LyricItem lyricItem = lyric.getLyricItem();
        if (lyricItem instanceof LyricText) {
            LyricText lyricText = (LyricText)lyricItem;
            for (LyricSyllable lyricSyllable : lyricText.getLyricSyllables()) {
                TextFontColor lyricElision = lyricSyllable.getLyricElision();
                if (lyricElision != null) buildElementWithValueAndAttribute("elision", lyricElision.getValue(), "xml:lang", lyricElision.getLang());
                buildElementWithValue("syllabic", BuilderUtil.enumValue(lyricSyllable.getSyllabic()));
                TextData lyricSyllableText = lyricSyllable.getText();
                buildElementWithValueAndAttribute("text", lyricSyllableText.getValue(), "xml:lang", lyricSyllableText.getLang());
            }
            buildExtend(lyricText.getExtend());
        }
        else if (lyricItem instanceof Extend) buildExtend((Extend)lyricItem);
        appendLine("</lyric>");
    }
}
