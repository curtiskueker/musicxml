package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.exception.TimeSignatureException;
import org.curtis.lilypond.util.NoteUtil;
import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.lilypond.util.TimeSignatureUtil;
import org.curtis.lilypond.util.TypeUtil;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.direction.directiontype.DirectionTypeList;
import org.curtis.musicxml.note.Chord;
import org.curtis.musicxml.note.Dot;
import org.curtis.musicxml.note.Forward;
import org.curtis.musicxml.note.FullNote;
import org.curtis.musicxml.note.FullNoteType;
import org.curtis.musicxml.note.LineType;
import org.curtis.musicxml.note.Notations;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.NoteType;
import org.curtis.musicxml.note.Notehead;
import org.curtis.musicxml.note.NoteheadType;
import org.curtis.musicxml.note.Pitch;
import org.curtis.musicxml.note.Rest;
import org.curtis.musicxml.note.Stem;
import org.curtis.musicxml.note.StemType;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.TupletNotes;
import org.curtis.musicxml.note.Unpitched;
import org.curtis.musicxml.note.notation.Articulations;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.ShowTuplet;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.SlurType;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.note.notation.articulation.Articulation;
import org.curtis.musicxml.note.notation.articulation.BreathMark;
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
    private List<Object> deferredNotations = new ArrayList<>();
    private Boolean isBeginBeam = false;
    private Boolean isEndBeam = false;

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

    public void setBeginBeam() {
        isBeginBeam = true;
    }

    public void setEndBeam() {
        isEndBeam = true;
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
        deferredNotationsBuild();

        return stringBuilder;
    }

    private StringBuilder noteTypeBuild() throws BuildException {
        if (note.getCue() || !TypeUtil.getBooleanDefaultYes(note.getPrintout().getPrintObject())) {
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
            Boolean measure = TypeUtil.getBoolean(rest.getMeasure());
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
            for(int i = 1; i <= iterations; i++) append("'");
        } else if(octave < 3) {
            int iterations = 3 - octave;
            for(int i = 1; i <= iterations; i++) append(",");
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

        List<Slur> slurs = note.getSlurs();
        for (Slur slur : slurs) {
            Connection connectionType = slur.getConnectionType();
            if (connectionType != Connection.START) continue;

            SlurType slurType = slur.getSlurType();
            LineType lineType = slur.getLineType();
            append("\\");
            if (slurType == null) {
                append("slur");
            } else {
                switch (slurType) {
                    case NORMAL:
                        append("slur");
                        break;
                    case PHRASING:
                        append("phrasingSlur");
                        break;
                }
            }
            if (lineType == null) {
                append("Solid");
            } else {
                switch (lineType) {
                    case SOLID:
                        append("Solid");
                        break;
                    case DASHED:
                        append("Dashed");
                        break;
                }
            }
            append(" ");
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
            } else if (fullNoteType instanceof Rest && TypeUtil.getBoolean(((Rest)fullNoteType).getMeasure())) {
                append(TimeSignatureUtil.getWholeMeasureRepresentation());
            } else if (noteType != null) {
                noteTypeValueBuild();
            } else if (MathUtil.isPositive(duration)) {
                append(TimeSignatureUtil.getDurationRepresentationValue(duration));
            }
        } catch (TimeSignatureException e) {
            throw new BuildException(e.getMessage() + "  Skipping note.");
        }

        return stringBuilder;
    }

    private StringBuilder noteTypeValueBuild() {
        NoteType noteType = note.getType();
        if (noteType == null) return stringBuilder;
        append(NoteUtil.getNoteTypeValue(noteType.getValue()));
        for(Dot dot : note.getDots()) append(".");

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
            if (!TypeUtil.getBooleanDefaultYes(notations.getPrintObject())) continue;

            for(Notation notation : notations.getNotations()) {
                if (notation instanceof Articulations) {
                    Articulations articulations = (Articulations)notation;
                    List<Articulation> articulationList = articulations.getArticulationList();
                    List<Articulation> articulationListCopy = new ArrayList<>();
                    for (Articulation articulation : articulationList) {
                        if (articulation instanceof BreathMark) {
                            deferredNotations.add(articulation);
                        } else {
                            articulationListCopy.add(articulation);
                        }
                    }
                    articulations.setArticulationList(articulationListCopy);
                }

                MusicDataBuilder musicDataBuilder = new MusicDataBuilder(notation);
                append(musicDataBuilder.build().toString());
            }
        }

        return stringBuilder;
    }

    private StringBuilder beamBuild() {
        if(isBeginBeam) {
            append("[");
        } else if(isEndBeam) {
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

        int directionTypeCount = directions.stream().flatMap(typeList -> typeList.getDirectionTypeLists().stream()).mapToInt(direction -> direction.getDirectionTypes().size()).sum();
        directions.forEach(DirectionBuilder::setDirectionDefaults);

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
        Iterator<DirectionTypeList> directionTypeListIterator = direction.getDirectionTypeLists().iterator();
        DirectionTypeList directionTypeList = directionTypeListIterator.next();
        Iterator<DirectionType> directionTypeIterator = directionTypeList.getDirectionTypes().iterator();
        DirectionType directionType;

        append(" { ");

        // for each direction type, build the spacer then the direction type with placement
        for (int spacerIndex = 1; spacerIndex <= spacerCount; spacerIndex++) {
            try {
                append(NoteUtil.getSpacerRepresentation(spacerDuration));
                if (!directionTypeIterator.hasNext()) {
                    if (directionTypeListIterator.hasNext()) {
                        directionTypeList = directionTypeListIterator.next();
                        directionTypeIterator = directionTypeList.getDirectionTypes().iterator();
                    } else {
                        if (directionIterator.hasNext()) {
                            direction = directionIterator.next();
                            directionTypeListIterator = direction.getDirectionTypeLists().iterator();
                            directionTypeList = directionTypeListIterator.next();
                            directionTypeIterator = directionTypeList.getDirectionTypes().iterator();
                        } else {
                            break;
                        }
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
        deferredNotationsBuild();

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
            for (DirectionTypeList directionTypeList : direction.getDirectionTypeLists()) {
                for (DirectionType directionType : directionTypeList.getDirectionTypes()) {
                    String name = directionType.getClass().getSimpleName();
                    Integer directionTypeCount = directionTypeCounts.computeIfAbsent(name, count -> 0);
                    directionTypeCount++;
                    directionTypeCounts.put(name, directionTypeCount);
                }
            }
        }

        for (String multipleDirectionType : DirectionType.MULTIPLE_DIRECTION_TYPES) {
            Integer directionTypeCount = directionTypeCounts.get(multipleDirectionType);
            if (directionTypeCount != null && directionTypeCount > 1) return true;
        }

        return false;
    }

    private StringBuilder deferredNotationsBuild() throws BuildException {
        for (Object notation : deferredNotations) {
            MusicDataBuilder musicDataBuilder = new MusicDataBuilder(notation);
            append(" ");
            append(musicDataBuilder.build().toString());
        }

        return stringBuilder;
    }
}
