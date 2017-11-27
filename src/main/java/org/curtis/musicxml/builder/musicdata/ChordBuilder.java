package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.common.Connection;
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

        for (Note note : notes) {
            Connection chordType = note.getFullNote().getChordType();
            if (chordType == Connection.START || chordType == Connection.SINGLE) {
                NoteBuilder noteBuilder = new NoteBuilder(note);
                append(noteBuilder.preChordBuild().toString());
            }
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

        for (Note note : notes) {
            Connection chordType = note.getFullNote().getChordType();
            if (chordType == Connection.SINGLE || chordType == Connection.STOP) {
                NoteBuilder noteBuilder = new NoteBuilder(note);
                append(noteBuilder.postChordBuild().toString());
            }
        }

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
