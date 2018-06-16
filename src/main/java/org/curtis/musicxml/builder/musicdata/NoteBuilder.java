package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
import org.curtis.musicxml.builder.FormattingBuilder;
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
import org.curtis.musicxml.note.NoteheadAccidentalText;
import org.curtis.musicxml.note.NoteheadDisplayText;
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
        FormattingBuilder.buildFont(note.getFont()).forEach((k, v) -> buildAttribute(k, v));
        buildAttribute("color", note.getColor());
        FormattingBuilder.buildPrintout(note.getPrintout()).forEach((k, v) -> buildAttribute(k, v));
        buildAttribute("dynamics", BuilderUtil.stringValue(note.getDynamics()));
        buildAttribute("end-dynamics", BuilderUtil.stringValue(note.getEndDynamics()));
        buildAttribute("attack", BuilderUtil.stringValue(note.getAttack()));
        buildAttribute("release", BuilderUtil.stringValue(note.getRelease()));
        buildAttribute("time-only", note.getTimeOnly());
        buildAttribute("pizzicato", BuilderUtil.yesOrNo(note.getPizzicato()));
        appendLine(">");
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
        if (grace == null) buildElementWithValue("duration", BuilderUtil.stringValue(note.getDuration()));
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
            accidentalAttributes.putAll(FormattingBuilder.buildLevelDisplay(accidental.getLevelDisplay()));
            accidentalAttributes.putAll(FormattingBuilder.buildPrintStyle(accidental.getPrintStyle()));
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
            Map<String, String> stemAttributes = new HashMap<>();
            YPosition yPosition = stem.getyPosition();
            if (yPosition != null) {
                stemAttributes.put("default-x", BuilderUtil.stringValue(yPosition.getDefaultX()));
                stemAttributes.put("default-y", BuilderUtil.stringValue(yPosition.getDefaultY()));
                stemAttributes.put("relative-x", BuilderUtil.stringValue(yPosition.getRelativeX()));
                stemAttributes.put("relative-y", BuilderUtil.stringValue(yPosition.getRelativeY()));
            }
            stemAttributes.put("color", stem.getColor());
            buildElementWithValueAndAttributes("stem", BuilderUtil.enumValue(stem.getType()), stemAttributes);
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
            noteheadAttributes.putAll(FormattingBuilder.buildFont(notehead.getFont()));
            noteheadAttributes.put("color", notehead.getColor());
            buildElementWithValueAndAttributes("notehead", noteheadType, noteheadAttributes);
        }
        List<NoteheadText> noteheadTextList = note.getNoteheadTextList();
        if (!noteheadTextList.isEmpty()) {
            appendLine("<notehead-text>");
            for (NoteheadText noteheadText : noteheadTextList) {
                if (noteheadText instanceof NoteheadDisplayText) {
                    NoteheadDisplayText noteheadDisplayText = (NoteheadDisplayText)noteheadText;
                    buildFormattedText("display-text", noteheadDisplayText.getText());
                } else if (noteheadText instanceof NoteheadAccidentalText) {

                }
            }
            appendLine("</notehead_text>");
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
        appendLine("</note>");

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
        append("<notations");
        buildAttribute("print-object", BuilderUtil.yesOrNo(notations.getPrintObject()));
        appendLine(">");
        buildEditorial(notations.getEditorial());
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
        buildAttribute("justify", BuilderUtil.enumValue(lyric.getJustify()));
        PlacementBuilder.buildPosition(lyric.getPosition()).forEach((k, v) -> buildAttribute(k, v));
        buildAttribute("placement", BuilderUtil.enumValue(lyric.getPlacement()));
        buildAttribute("color", lyric.getColor());
        buildAttribute("print-object", BuilderUtil.yesOrNo(lyric.getPrintObject()));
        appendLine(">");
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
                buildElementWithValue("syllabic", BuilderUtil.enumValue(lyricSyllable.getSyllabic()));
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
        appendLine("</lyric>");
    }
}
