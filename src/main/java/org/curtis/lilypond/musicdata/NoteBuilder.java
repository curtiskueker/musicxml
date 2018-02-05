package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.exception.TimeSignatureException;
import org.curtis.lilypond.util.NoteUtil;
import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.note.Chord;
import org.curtis.musicxml.note.Forward;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.FullNoteType;
import org.curtis.musicxml.note.Notations;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.NoteType;
import org.curtis.musicxml.note.Notehead;
import org.curtis.musicxml.note.NoteheadType;
import org.curtis.musicxml.note.Pitch;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.Rest;
import org.curtis.musicxml.note.Stem;
import org.curtis.musicxml.note.StemType;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.TupletNotes;
import org.curtis.musicxml.note.Unpitched;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.lilypond.util.TimeSignatureUtil;
import org.curtis.musicxml.note.notation.ShowTuplet;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NoteBuilder extends MusicDataBuilder {
    private Note note;
    private List<Direction> nonMultipleDirections = new ArrayList<>();

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

    public List<Direction> getNonMultipleDirections() {
        return nonMultipleDirections;
    }

    public void setNonMultipleDirections(List<Direction> nonMultipleDirections) {
        this.nonMultipleDirections = nonMultipleDirections;
    }

    public StringBuilder build() throws BuildException {
        preNoteBuild();
        preNonMultipleDirectionBuild();
        startGraceBuild();
        noteTypeBuild();
        noteDurationBuild();
        beamBuild();
        notationsBuild();
        finishGraceBuild();
        nonMultipleDirectionBuild();
        postNonMultipleDirectionBuild();

        return stringBuilder;
    }

    private StringBuilder noteTypeBuild() throws BuildException {
        if (!note.getPrintout().getPrintObject()) {
            append(" s");
            return stringBuilder;
        }

        FullNote fullNote = note.getFullNote();
        FullNoteType fullNoteType = fullNote.getFullNoteType();

        if (fullNoteType instanceof Pitch) {
            append(" ");

            Pitch pitch = (Pitch)fullNoteType;
            append(NoteUtil.getStep(pitch.getStep()));
            append(NoteUtil.getAlter(pitch.getAlter()));

            pitchOctaveBuild(pitch.getOctave());
        } else if (fullNoteType instanceof Unpitched) {
            Unpitched unpitched = (Unpitched)fullNoteType;
            append(NoteUtil.getStep(unpitched.getDisplayStep()));
            pitchOctaveBuild(unpitched.getDisplayOctave() - 1);
        } else if (fullNoteType instanceof Rest) {
            Rest rest = (Rest)fullNoteType;
            Boolean measure = rest.getMeasure();
            NoteType noteType = note.getType();
            BigDecimal duration = note.getDuration();
            BigDecimal wholeMeasureDuration = null;
            try {
                wholeMeasureDuration = TimeSignatureUtil.getWholeMeasureDuration();
            } catch (TimeSignatureException e) {
                throw new BuildException(e.getMessage());
            }
            if(measure || noteType == null || MathUtil.equalTo(duration, wholeMeasureDuration)) {
                rest.setMeasure(true);
                append("R");
            } else {
                append("r");
            }
        }

        return stringBuilder;
    }

    private void pitchOctaveBuild(Integer octave) {
        if(octave == null) return;

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

    private StringBuilder preNoteBuild() {
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

        Notehead notehead = note.getNotehead();
        if (notehead != null) {
            NoteheadType noteheadType = notehead.getType();
            if (noteheadType != null) {
                switch (noteheadType) {
                    case CROSS:
                    case X:
                        append("\\xNote ");
                }
            }
        }

        return stringBuilder;
    }

    private StringBuilder noteDurationBuild() throws BuildException {
        NoteType noteType = note.getType();
        FullNoteType fullNoteType = note.getFullNote().getFullNoteType();
        BigDecimal duration = note.getDuration();
        if (fullNoteType instanceof Rest && ((Rest)fullNoteType).getMeasure()) {
            try {
                append(TimeSignatureUtil.getWholeMeasureRepresentation());
            } catch (TimeSignatureException e) {
                throw new BuildException(e.getMessage());
            }
        } else if (noteType != null) {
            append(NoteUtil.getNoteTypeValue(noteType.getValue()));
            List<Placement> dots = note.getDots();
            for(Placement dot : dots) {
                append(".");
            }
        } else if (MathUtil.isPositive(duration)) {
            try {
                append(TimeSignatureUtil.getDurationRepresentationValue(duration));
            } catch (TimeSignatureException e) {
                throw new BuildException(e.getMessage());
            }
        }

        return stringBuilder;
    }

    private StringBuilder startGraceBuild() {
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

    private StringBuilder finishGraceBuild() {
        if(note.isGraceNote() && note.getGrace().getGraceType() == Connection.STOP) {
            append(" }");
        }

        return stringBuilder;
    }

    private StringBuilder notationsBuild() throws BuildException {
        for(Notations notations : note.getNotationsList()) {
            for(Notation notation : notations.getNotations()) {
                MusicDataBuilder musicDataBuilder = new MusicDataBuilder(notation);
                append(musicDataBuilder.build().toString());
            }
        }

        return stringBuilder;
    }

    private StringBuilder beamBuild() {
        if(note.getBeginBeam()) {
            append("[");
        } else if(note.getEndBeam()) {
            append("]");
        }

        return stringBuilder;
    }

    private StringBuilder preNonMultipleDirectionBuild() {
        if (!nonMultipleDirections.isEmpty()) {
            append(" << ");
        }

        return stringBuilder;
    }

    private StringBuilder nonMultipleDirectionBuild() throws BuildException {
        int directionTypeCount = 0;
        for (Direction direction : nonMultipleDirections) {
            directionTypeCount += direction.getDirectionTypes().size();
        }

        if (directionTypeCount < 2) return stringBuilder;

        for(Direction direction : nonMultipleDirections) {
            DirectionBuilder.setDirectionDefaults(direction);
        }

        // create list spacers with durations
        // get the iterator to the list
        int spacerCount = Integer.highestOneBit(directionTypeCount - 1) << 1;
        BigDecimal spacerDuration = MathUtil.divide(note.getDuration(), MathUtil.newBigDecimal(spacerCount));
        if (!MathUtil.isPositive(spacerDuration)) {
            System.err.println("Warning: multiple dynamics directions on zero-duration note.  Skipping.");
            return stringBuilder;
        }

        Iterator<Direction> directionIterator = nonMultipleDirections.iterator();
        Direction direction = directionIterator.next();
        Iterator<DirectionType> directionTypeIterator = direction.getDirectionTypes().iterator();
        DirectionType directionType;

        append(" { ");

        // for each direction type, build the spacer then the direction type with placement
        for (int spacerIndex = 1; spacerIndex <= spacerCount; spacerIndex++) {
            try {
                append(NoteUtil.getSpacerRepresentation(spacerDuration));
                if (!directionTypeIterator.hasNext()) {
                    if (directionIterator.hasNext()) {
                        direction = directionIterator.next();
                        directionTypeIterator = direction.getDirectionTypes().iterator();
                    } else {
                        break;
                    }
                }
                directionType = directionTypeIterator.next();
                append(PlacementBuildUtil.getPlacement(direction.getPlacement()));
                append(new MusicDataBuilder(directionType).build().toString());
                append(" ");
            } catch (TimeSignatureException e) {
                throw new BuildException(e.getMessage());
            }
        }

        append("} ");

        return stringBuilder;
    }

    private StringBuilder postNonMultipleDirectionBuild() {
        if (!nonMultipleDirections.isEmpty()) {
            append(" >> ");
        }

        return stringBuilder;
    }

    public StringBuilder buildNote(Note note) throws BuildException {
        this.note = note;
        nonMultipleDirections.addAll(note.getMultipleDirections());
        return build();
    }

    public StringBuilder buildChord(Chord chord) throws BuildException {
        List<Note> notes = chord.getNotes();
        List<NoteBuilder> noteBuilders = new ArrayList<>();

        for(Note note : notes) {
            NoteBuilder noteBuilder = new NoteBuilder(note);
            noteBuilders.add(noteBuilder);
            nonMultipleDirections.addAll(note.getMultipleDirections());
        }

        for (NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            Note note = noteBuilder.getNote();
            Connection chordType = note.getFullNote().getChordType();
            if (chordType == Connection.START || chordType == Connection.SINGLE) {
                append(noteBuilder.preNoteBuild().toString());
            }
        }

        preNonMultipleDirectionBuild();

        append("<");

        for(NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            append(noteBuilder.noteTypeBuild().toString());
        }

        append(">");

        for (NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            Note note = noteBuilder.getNote();
            Connection chordType = note.getFullNote().getChordType();
            if (chordType == Connection.SINGLE || chordType == Connection.STOP) {
                append(noteBuilder.noteDurationBuild().toString());
            }
        }

        for (NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            append(noteBuilder.beamBuild().toString());
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
            append(noteBuilder.finishGraceBuild().toString());
        }

        for (NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            Note note = noteBuilder.getNote();
            Connection chordType = note.getFullNote().getChordType();
            if (chordType == Connection.START || chordType == Connection.SINGLE) {
                noteBuilder.getNonMultipleDirections().clear();
                noteBuilder.getNonMultipleDirections().addAll(nonMultipleDirections);
                append(noteBuilder.nonMultipleDirectionBuild().toString());
            }
        }

        postNonMultipleDirectionBuild();

        return stringBuilder;
    }

    public StringBuilder buildTupletNotes(TupletNotes tupletNotes) throws BuildException {
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

    public StringBuilder buildForward(Forward forward) throws BuildException {
        BigDecimal duration = forward.getDuration();

        try {
            append(NoteUtil.getSpacerRepresentation(duration));
        } catch (TimeSignatureException e) {
            throw new BuildException(e.getMessage());
        }

        return stringBuilder;
    }
}
