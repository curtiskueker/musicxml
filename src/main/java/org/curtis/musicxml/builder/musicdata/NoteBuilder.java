package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.Grace;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.Pitch;
import org.curtis.musicxml.note.Step;

public class NoteBuilder extends MusicDataBuilder {
    private Note note;

    public NoteBuilder(Note note) {
        super(note);
        this.note = note;
    }

    public StringBuilder build() {
        FullNote fullNote = note.getFullNote();
        if(fullNote != null) {
            Grace grace = note.getGrace();
            if(grace != null) {
                append("\\grace ");
            }

            Pitch pitch = fullNote.getPitch();
            if(pitch != null) {
                Step step = pitch.getStep();
                switch (step) {
                    case A:
                        append("a");
                        break;
                    case B:
                        append("b");
                        break;
                    case C:
                        append("c");
                        break;
                    case D:
                        append("d");
                        break;
                    case E:
                        append("e");
                        break;
                    case F:
                        append("f");
                        break;
                    case G:
                        append("g");
                        break;
                }

                Integer octave = pitch.getOctave();
                if(octave > 3) {
                    int iterations = octave - 3;
                    for(int i = 1; i <= iterations; i++) {
                        append("'");
                    }
                } else if(octave < 3) {
                    int iterations = 3 - octave;
                    for(int i = 1; i <= iterations; i++) {
                        append(",");
                    }
                }

                // TODO: replace temporary duration
                append("4");
            }
        }

        append(" ");

        return stringBuilder;
    }
}
