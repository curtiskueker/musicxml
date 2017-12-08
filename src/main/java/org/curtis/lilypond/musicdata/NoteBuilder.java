package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.PartBuilder;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.note.Chord;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.FullNoteType;
import org.curtis.musicxml.note.Notations;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.NoteType;
import org.curtis.musicxml.note.NoteTypeValue;
import org.curtis.musicxml.note.Pitch;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.Rest;
import org.curtis.musicxml.note.Stem;
import org.curtis.musicxml.note.StemType;
import org.curtis.musicxml.note.Step;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.TupletNotes;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.handler.util.TimeSignatureUtil;
import org.curtis.musicxml.note.notation.ShowTuplet;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NoteBuilder extends MusicDataBuilder {
    private Note note;

    public NoteBuilder(Note note) {
        super(note);
        this.note = note;
    }

    public NoteBuilder() {

    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public StringBuilder build() {
        preChordBuild();
        mainBuild();
        postChordBuild();

        if(note.getBeginBeam()) {
            append("[");
        } else if(note.getEndBeam()) {
            append("]");
        }

        notationsBuild();
        postGraceBuild();

        return stringBuilder;
    }

    protected StringBuilder mainBuild() {
        preGraceBuild();

        FullNote fullNote = note.getFullNote();
        FullNoteType fullNoteType = fullNote.getFullNoteType();

        if (fullNoteType instanceof Pitch) {
            append(" ");

            Pitch pitch = (Pitch)fullNoteType;
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

        if (fullNoteType instanceof Rest) {
            Rest rest = (Rest)fullNoteType;
            Boolean measure = rest.getMeasure();
            if(measure || noteType == null) {
                append("R");
                append(TimeSignatureUtil.getWholeMeasureNoteRepresentation(PartBuilder.getCurrentTimeSignature()));
            } else {
                append("r");
            }
        }

        return stringBuilder;
    }

    protected StringBuilder preChordBuild() {
        append(" ");

        Stem stem = note.getStem();
        if(stem != null) {
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

        return stringBuilder;
    }

    protected StringBuilder postChordBuild() {
        NoteType noteType = note.getType();

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

            List<Placement> dots = note.getDots();
            for(Placement dot : dots) {
                append(".");
            }
        }

        return stringBuilder;
    }

    protected StringBuilder preGraceBuild() {
        if (note.isGraceNote()) {
            if (note.getGrace().getGraceType() == Connection.START || note.getGrace().getGraceType() == Connection.SINGLE) {
                if(note.getGrace().getSlash()) {
                    append("\\slashedGrace ");
                } else {
                    append("\\grace ");
                }
            }
            if(note.getGrace().getGraceType() == Connection.START) {
                append("{ ");
            }
        }

        return stringBuilder;
    }

    protected StringBuilder postGraceBuild() {
        if(note.isGraceNote() && note.getGrace().getGraceType() == Connection.STOP) {
            append(" }");
        }

        return stringBuilder;
    }

    protected StringBuilder notationsBuild() {
        for(Notations notations : note.getNotationsList()) {
            for(Notation notation : notations.getNotations()) {
                MusicDataBuilder musicDataBuilder = new MusicDataBuilder(notation);
                append(musicDataBuilder.build().toString());
            }
        }

        return stringBuilder;
    }

    public StringBuilder buildNote(Note note) {
        this.note = note;
        return build();
    }

    public StringBuilder buildChord(Chord chord) {
        List<Note> notes = chord.getNotes();
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

        for(Direction direction : chord.getDirections()) {
            MusicDataBuilder directionBuilder = new MusicDataBuilder(direction);
            append(directionBuilder.build().toString());
        }

        for(NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            append(noteBuilder.postGraceBuild().toString());
        }

        return stringBuilder;
    }

    public StringBuilder buildTupletNotes(TupletNotes tupletNotes) {
        List<MusicData> musicDataList = tupletNotes.getMusicDataList();
        if(musicDataList == null || musicDataList.isEmpty() || musicDataList.size() < 2) {
            return stringBuilder;
        }

        Note startNote = null;
        for(MusicData musicData : musicDataList) {
            if(musicData instanceof Note) {
                Note note = (Note)musicData;
                Tuplet tuplet = note.getTuplet();
                if (tuplet != null && tuplet.getType() == Connection.START) {
                    startNote = note;
                    break;
                }
            } else if(musicData instanceof Chord) {
                Chord chordNotes = (Chord)musicData;
                for(Note note : chordNotes.getNotes()) {
                    Tuplet tuplet = note.getTuplet();
                    if (tuplet != null && tuplet.getType() == Connection.START) {
                        startNote = note;
                        break;
                    }
                }
            }
        }

        if(startNote == null) {
            return stringBuilder;
        }

        Tuplet startTuplet = startNote.getTuplet();
        if(startTuplet == null) {
            return stringBuilder;
        }

        if(startTuplet.getShowNumber() == ShowTuplet.NONE) {
            append(" \\once \\omit TupletNumber");
        }

        if(!startTuplet.getBracket()) {
            append (" \\once \\override TupletBracket.bracket-visibility = ##f");
        }

        if(startTuplet.getPlacement() == Location.ABOVE) {
            append(" \\tupletUp");
        } else if(startTuplet.getPlacement() == Location.BELOW) {
            append(" \\tupletDown");
        }

        append(" \\tuplet");

        // dependent on time-modification subelements actual-notes and normal-notes
        TimeModification timeModification = startNote.getTimeModification();
        append(" ");
        append(String.valueOf(timeModification.getActualNotes()));
        append("/");
        append(String.valueOf(timeModification.getNormalNotes()));

        append(" {");

        for(MusicData musicData : musicDataList) {
            MusicDataBuilder musicDataBuilder = new MusicDataBuilder(musicData);
            append(musicDataBuilder.build().toString());
        }

        append(" }");

        return stringBuilder;
    }
}
