package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.note.ChordNotes;
import org.curtis.musicxml.note.Note;

import java.util.ArrayList;
import java.util.List;

public class ChordBuilder extends MusicDataBuilder {
    private ChordNotes chordNotes;

    public ChordBuilder(ChordNotes chordNotes) {
        super(chordNotes);
        this.chordNotes = chordNotes;
    }

    public StringBuilder build() {
        List<Note> notes = chordNotes.getNotes();
        List<NoteBuilder> noteBuilders = new ArrayList<>();

        for(Note note : notes) {
            NoteBuilder noteBuilder = new NoteBuilder(note);
            noteBuilders.add(noteBuilder);
        }

        for (NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            Note note = noteBuilder.getNote();
            Connection chordType = note.getFullNote().getChordType();
            if (chordType == Connection.START || chordType == Connection.SINGLE) {
                append(noteBuilder.preChordBuild().toString());
            }
        }

        append("<");

        for(NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            Note note = noteBuilder.getNote();
            append(noteBuilder.mainBuild().toString());

            if(note.getBeginBeam()) {
                append("[");
            }
        }

        append(">");

        for (NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            Note note = noteBuilder.getNote();
            Connection chordType = note.getFullNote().getChordType();
            if (chordType == Connection.SINGLE || chordType == Connection.STOP) {
                append(noteBuilder.postChordBuild().toString());
            }
        }

        for (NoteBuilder noteBuilder : noteBuilders) {
            Note note = noteBuilder.getNote();
            if(note.getEndBeam()) {
                append("]");
            }
        }

        for(NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            append(noteBuilder.notationsBuild().toString());
        }

        for(Direction direction : chordNotes.getDirections()) {
            DirectionBuilder directionBuilder = new DirectionBuilder(direction);
            append(directionBuilder.build().toString());
        }

        for(NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            append(noteBuilder.postGraceBuild().toString());
        }

        return stringBuilder;
    }
}
