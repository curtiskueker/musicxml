package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.builder.BaseBuilder;
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
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteBuilder extends BaseBuilder {
    private Note note;

    public NoteBuilder() {}

    public NoteBuilder(Note note) {
        this.note = note;
    }

    public StringBuilder build() {
        if (note == null) return stringBuilder;

        appendLine("<note>");
        Grace grace = note.getGrace();
        if (grace != null) buildElement("grace");
        buildFullNote(note.getFullNote());
        // TODO: duration
        buildElementWithValue("duration", 1);
        // TODO: tie type
        for (Tie tie : note.getTies()) buildElementWithAttribute("tie", "type", "start");
        String instrument = note.getInstrument();
        if (StringUtil.isNotEmpty(instrument)) buildElementWithAttribute("instrument", "id", instrument);
        NoteType noteType = note.getType();
        if (noteType != null) {
            buildElementWithValue("type", BuilderUtil.noteTypeValue(noteType.getValue()));
        }
        Accidental accidental = note.getAccidental();
        // TODO: accidental-value
        if (accidental != null) buildElementWithValue("accidental", "sharp");
        TimeModification timeModification = note.getTimeModification();
        if (timeModification != null) {
            appendLine("<time-modification>");
            NoteBuilder noteBuilder = new NoteBuilder();
            append(noteBuilder.buildTimeModification(note.getTimeModification()).toString());
            appendLine("</time-modification>");
        }
        Stem stem = note.getStem();
        if (stem != null) {
            buildElementWithValue("stem", BuilderUtil.enumValue(stem.getType()));
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
            buildElementWithValue("notehead", noteheadType);
        }
        List<NoteheadText> noteheadTextList = note.getNoteheadTextList();
        if (!noteheadTextList.isEmpty()) buildElement("notehead-text");
        List<Beam> beams = note.getBeams();
        if (!beams.isEmpty()) {
            for (Beam beam : beams) {
                Map<String, String> attributes = new HashMap<>();
                attributes.put("number", BuilderUtil.stringValue(beam.getNumber()));
                attributes.put("fan", BuilderUtil.enumValue(beam.getFan()));
                buildElementWithValueAndAttributes("beam", buildBeamType(beam.getType()), attributes);
            }
        }
        List<Notations> notationsList = note.getNotationsList();
        if (!notationsList.isEmpty()) {
            for (Notations notations : notationsList) buildNotations(notations);
        }
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
        buildElement("rest");
    }

    public StringBuilder buildTimeModification(TimeModification timeModification) {
        clear();
        if (timeModification == null) return stringBuilder;

        buildElementWithValue("actual-notes", timeModification.getActualNotes());
        buildElementWithValue("normal-notes", timeModification.getNormalNotes());
        buildElementWithValue("normal-type", BuilderUtil.noteTypeValue(timeModification.getNormalType()));

        return stringBuilder;
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
}
