package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.note.ChordNotes;
import org.curtis.musicxml.note.Note;

import java.util.List;

public class ChordBuilder extends MusicDataBuilder {
    private ChordNotes chordNotes;

    public ChordBuilder(ChordNotes chordNotes) {
        super(chordNotes);
        this.chordNotes = chordNotes;
    }

    public StringBuilder build() {
        List<Note> notes = chordNotes.getNotes();

        for(Note note : notes) {
            NoteBuilder noteBuilder = new NoteBuilder(note);
            append(noteBuilder.preChordBuild().toString());
        }

        append("<");

        for(Note note : notes) {
            NoteBuilder noteBuilder = new NoteBuilder(note);
            append(noteBuilder.mainBuild().toString());

            if(note.getBeginBeam()) {
                append("[");
            }
        }

        append(">");

        Note firstNote = notes.get(0);
        NoteBuilder firstNoteBuilder = new NoteBuilder(firstNote);
        append(firstNoteBuilder.postChordBuild().toString());

        for (Note note : notes) {
            if(note.getEndBeam()) {
                append("]");
            }
        }

        for(Note note : notes) {
            NoteBuilder noteBuilder = new NoteBuilder(note);
            append(noteBuilder.notationsBuild().toString());
        }

        for(Direction direction : chordNotes.getDirections()) {
            DirectionBuilder directionBuilder = new DirectionBuilder(direction);
            append(directionBuilder.build().toString());
        }

        for(Note note : notes) {
            NoteBuilder noteBuilder = new NoteBuilder(note);
            append(noteBuilder.postGraceBuild().toString());
        }

        return stringBuilder;
    }
}
