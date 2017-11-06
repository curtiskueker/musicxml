package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.Grace;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.NoteType;
import org.curtis.musicxml.note.NoteTypeValue;
import org.curtis.musicxml.note.Pitch;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.Rest;
import org.curtis.musicxml.note.Stem;
import org.curtis.musicxml.note.StemType;
import org.curtis.musicxml.note.Step;
import org.curtis.musicxml.util.TimeSignatureUtil;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;
import java.util.List;

public class NoteBuilder extends MusicDataBuilder {
    private Note note;

    public NoteBuilder(Note note) {
        super(note);
        this.note = note;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public StringBuilder build() {
        FullNote fullNote = note.getFullNote();

        Stem stem = note.getStem();
        if(stem != null && !fullNote.isContinueChord() && !fullNote.isEndChord()) {
            StemType stemType = stem.getType();
            switch (stemType) {
                case DOWN:
                    append("\\stemDown ");
                    break;
                case UP:
                    append("\\stemUp ");
                    break;
            }
        }

        if(fullNote.isBeginChord()) {
            append("<");
        }

        Grace grace = note.getGrace();
        if(grace != null) {
            append("\\grace ");
        }

        if (fullNote instanceof Pitch) {
            Pitch pitch = (Pitch)fullNote;
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

            //TODO: alter single and double accidentals only
            BigDecimal alter = pitch.getAlter();
            if(MathUtil.equalTo(alter, MathUtil.newBigDecimal(-2))) {
                append("eses");
            } else if(MathUtil.equalTo(alter, MathUtil.newBigDecimal(-1))) {
                append("es");
            } else if(MathUtil.equalTo(alter, MathUtil.newBigDecimal(1))) {
                append("is");
            } else if(MathUtil.equalTo(alter, MathUtil.newBigDecimal(2))) {
                append("isis");
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
        }

        NoteType noteType = note.getType();

        if (fullNote instanceof Rest) {
            Rest rest = (Rest)fullNote;
            Boolean measure = rest.getMeasure();
            if(measure || noteType == null) {
                append("R");
                append(TimeSignatureUtil.getWholeMeasureNoteRepresentation(getCurrentTimeSignature()));
            } else {
                append("r");
            }
        }

        if(fullNote.isEndChord()) {
            append(">");
        }

        if (!fullNote.isBeginChord() && !fullNote.isContinueChord()) {
            if (noteType != null) {
                NoteTypeValue noteTypeValue = noteType.getValue();
                switch (noteTypeValue) {
                    case _1024TH:
                        append("1024");
                        break;
                    case _512TH:
                        append("512");
                        break;
                    case _256TH:
                        append("256");
                        break;
                    case _128TH:
                        append("128");
                        break;
                    case _64TH:
                        append("64");
                        break;
                    case _32ND:
                        append("32");
                        break;
                    case _16TH:
                        append("16");
                        break;
                    case EIGHTH:
                        append("8");
                        break;
                    case QUARTER:
                        append("4");
                        break;
                    case HALF:
                        append("2");
                        break;
                    case WHOLE:
                        append("1");
                        break;
                    case BREVE:
                        append("\\breve");
                        break;
                    case LONG:
                        append("\\longa");
                        break;
                    case MAXIMA:
                        append("\\maxima");
                        break;
                }
            }

            List<Placement> dots = note.getDots();
            for(Placement dot : dots) {
                append(".");
            }
        }

        if(note.getBeginBeam()) {
            append("[");
        } else if(note.getEndBeam()) {
            append("]");
        }

        append(" ");

        return stringBuilder;
    }
}
