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
import org.curtis.musicxml.note.notation.ornament.Tremolo;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NoteBuilder extends MusicDataBuilder {
    private Note note;
    private List<Direction> directions = new ArrayList<>();

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

    public List<Direction> getDirections() {
        return directions;
    }

    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    public StringBuilder build() throws BuildException {
        preNoteBuild();
        preDirectionBuild();
        startGraceBuild();
        noteTypeBuild();
        noteDurationBuild();
        beamBuild();
        notationsBuild();
        finishGraceBuild();
        directionBuild();
        postDirectionBuild();
        postNoteBuild();

        return stringBuilder;
    }

    private StringBuilder noteTypeBuild() throws BuildException {
        if (note.getCue() || !note.getPrintout().getPrintObject()) {
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
            pitchOctaveBuild(unpitched.getDisplayOctave());
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

    private StringBuilder preNoteBuild() throws BuildException {
        append(" ");

        Tremolo tremolo = note.getTremolo();
        if (tremolo != null && tremolo.getType() == Connection.START) {
            append("\\repeat tremolo ");
            noteTypeValueBuild();
            append(" { ");
        }

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

    private StringBuilder postNoteBuild() throws BuildException {
        Tremolo tremolo = note.getTremolo();
        if (tremolo != null && tremolo.getType() == Connection.STOP) {
            append(" } ");
        }

        return stringBuilder;
    }

    private StringBuilder noteDurationBuild() throws BuildException {
        NoteType noteType = note.getType();
        FullNoteType fullNoteType = note.getFullNote().getFullNoteType();
        BigDecimal duration = note.getDuration();
        Tremolo tremolo = note.getTremolo();
        try {
            if (tremolo != null &&(tremolo.getType() == Connection.START || tremolo.getType() == Connection.STOP)) {
                append(TimeSignatureUtil.getDurationRepresentationValue(duration));
            } else if (fullNoteType instanceof Rest && ((Rest)fullNoteType).getMeasure()) {
                append(TimeSignatureUtil.getWholeMeasureRepresentation());
            } else if (noteType != null) {
                noteTypeValueBuild();
            } else if (MathUtil.isPositive(duration)) {
                append(TimeSignatureUtil.getDurationRepresentationValue(duration));
            }
        } catch (TimeSignatureException e) {
            throw new BuildException(e.getMessage());
        }

        return stringBuilder;
    }

    private StringBuilder noteTypeValueBuild() {
        NoteType noteType = note.getType();
        if (noteType == null) return stringBuilder;
        append(NoteUtil.getNoteTypeValue(noteType.getValue()));
        List<Placement> dots = note.getDots();
        for(Placement dot : dots) {
            append(".");
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
                if (!notation.getPrintObject()) continue;

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

    private StringBuilder preDirectionBuild() {
        if (hasMultipleDirections()) {
            append(" << ");
        }

        return stringBuilder;
    }

    private StringBuilder directionBuild() throws BuildException {
        if (!hasMultipleDirections()) {
            for (Direction direction : directions) {
                append(new MusicDataBuilder(direction).build().toString());
            }

            return stringBuilder;
        }

        int directionTypeCount = 0;
        for (Direction direction : directions) {
            directionTypeCount += direction.getDirectionTypes().size();
        }

        for(Direction direction : directions) {
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

        Iterator<Direction> directionIterator = directions.iterator();
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

    private StringBuilder postDirectionBuild() {
        if (hasMultipleDirections()) {
            append(" >> ");
        }

        return stringBuilder;
    }

    public StringBuilder buildNote(Note note) throws BuildException {
        this.note = note;
        directions.addAll(note.getDirections());
        return build();
    }

    public StringBuilder buildChord(Chord chord) throws BuildException {
        List<Note> notes = chord.getNotes();
        List<NoteBuilder> noteBuilders = new ArrayList<>();

        for(Note note : notes) {
            NoteBuilder noteBuilder = new NoteBuilder(note);
            noteBuilders.add(noteBuilder);
            directions.addAll(note.getDirections());
        }
        directions.addAll(chord.getDirections());

        for (NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            Note note = noteBuilder.getNote();
            Connection chordType = note.getFullNote().getChordType();
            if (chordType == Connection.START || chordType == Connection.SINGLE) {
                append(noteBuilder.preNoteBuild().toString());
            }
        }

        preDirectionBuild();

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

        if (!hasMultipleDirections()) {
            for(Direction direction : chord.getDirections()) {
                MusicDataBuilder directionBuilder = new MusicDataBuilder(direction);
                append(directionBuilder.build().toString());
            }
        }

        for(NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            append(noteBuilder.finishGraceBuild().toString());
        }

        if (hasMultipleDirections()) {
            for (NoteBuilder noteBuilder : noteBuilders) {
                noteBuilder.clear();
                Note note = noteBuilder.getNote();
                Connection chordType = note.getFullNote().getChordType();
                if (chordType == Connection.START || chordType == Connection.SINGLE) {
                    noteBuilder.getDirections().clear();
                    noteBuilder.getDirections().addAll(directions);
                    append(noteBuilder.directionBuild().toString());
                }
            }
        }

        postDirectionBuild();

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
            append(" ");
            append(NoteUtil.getSpacerRepresentation(duration));
        } catch (TimeSignatureException e) {
            throw new BuildException(e.getMessage());
        }

        return stringBuilder;
    }

    private boolean hasMultipleDirections() {
        Map<String, Integer> directionTypeCounts = new HashMap<>();
        for (Direction direction : directions) {
            for (DirectionType directionType : direction.getDirectionTypes()) {
                String name = directionType.getClass().getSimpleName();
                Integer directionTypeCount = directionTypeCounts.computeIfAbsent(name, count -> 0);
                directionTypeCount++;
                directionTypeCounts.put(name, directionTypeCount);
            }
        }

        for (String multipleDirectionType : DirectionType.MULTIPLE_DIRECTION_TYPES) {
            Integer directionTypeCount = directionTypeCounts.get(multipleDirectionType);
            if (directionTypeCount != null && directionTypeCount > 1) return true;
        }

        return false;
    }
}
