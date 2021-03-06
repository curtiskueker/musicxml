package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.exception.DurationException;
import org.curtis.lilypond.exception.TimeSignatureException;
import org.curtis.lilypond.part.PartBuilder;
import org.curtis.lilypond.util.NoteUtil;
import org.curtis.lilypond.util.PlacementBuildUtil;
import org.curtis.lilypond.util.TimeSignatureUtil;
import org.curtis.lilypond.util.TypeUtil;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.display.LevelDisplay;
import org.curtis.musicxml.common.OrderedGroup;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.directiontype.DirectionType;
import org.curtis.musicxml.direction.directiontype.DirectionTypeList;
import org.curtis.musicxml.direction.directiontype.Words;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.display.Placement;
import org.curtis.musicxml.note.Accidental;
import org.curtis.musicxml.note.Beam;
import org.curtis.musicxml.note.BeamType;
import org.curtis.musicxml.note.Chord;
import org.curtis.musicxml.note.Forward;
import org.curtis.musicxml.note.LineType;
import org.curtis.musicxml.note.Notations;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.NoteType;
import org.curtis.musicxml.note.NoteTypeValue;
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
import org.curtis.musicxml.note.notation.Fermata;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.ShowTuplet;
import org.curtis.musicxml.note.notation.Slur;
import org.curtis.musicxml.note.notation.SlurType;
import org.curtis.musicxml.note.notation.Tied;
import org.curtis.musicxml.note.notation.TiedType;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.note.notation.articulation.Articulation;
import org.curtis.musicxml.note.notation.articulation.BreathMark;
import org.curtis.musicxml.note.notation.ornament.Tremolo;
import org.curtis.musicxml.note.notation.ornament.TremoloType;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NoteBuilder extends MusicDataBuilder {
    private Note note;
    private List<Direction> directions = new ArrayList<>();
    private List<Object> deferredNotations = new ArrayList<>();
    public static Set<Integer> CURRENT_BEAMS = new HashSet<>();

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
        transferDirections(note);
        preNoteBuild();
        preDirectionBuild();
        startGraceBuild();
        noteTypeBuild();
        noteDurationBuild();
        beamBuild();
        notationsBuild(note.getNotationsList());
        finishGraceBuild();
        directionBuild();
        postDirectionBuild();
        postNoteBuild();
        deferredNotationsBuild();

        return stringBuilder;
    }

    private StringBuilder noteTypeBuild() throws BuildException {
        if (note.isNotPrinted()) {
            append(" s");
            return stringBuilder;
        }

        NoteType noteType = note.getNoteType();
        if (noteType instanceof Pitch) {
            append(" ");

            Pitch pitch = (Pitch)noteType;
            append(NoteUtil.getStep(pitch.getStep()));
            append(NoteUtil.getAlter(pitch.getAlter()));

            pitchOctaveBuild(pitch.getOctave());

            Accidental accidental = note.getAccidental();
            if (accidental != null) {
                LevelDisplay accidentalLevelDisplay = accidental.getLevelDisplay();
                if (accidentalLevelDisplay != null && TypeUtil.getBoolean(accidentalLevelDisplay.getParentheses())) append("?");
            }
        } else if (noteType instanceof Unpitched) {
            Unpitched unpitched = (Unpitched)noteType;
            append(NoteUtil.getStep(unpitched.getStep()));
            pitchOctaveBuild(unpitched.getOctave());
        } else if (noteType instanceof Rest) {
            Rest rest = (Rest)noteType;
            Boolean measure = TypeUtil.getBoolean(rest.getMeasure());
            BigDecimal duration = note.getDuration();
            BigDecimal wholeMeasureDuration;
            try {
                wholeMeasureDuration = TimeSignatureUtil.getWholeMeasureDuration();
            } catch (TimeSignatureException e) {
                throw new BuildException(e.getMessage());
            }
            if(measure || note.getNoteValue() == null || MathUtil.equalTo(duration, wholeMeasureDuration)) {
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

        if (note.isChangeStaff()) {
            append("\\change Staff = \"");
            append(note.getStaff());
            append("\" ");
        }

        Tremolo tremolo = note.getTremolo();
        if (tremolo != null && tremolo.getType() == TremoloType.START) {
            append("\\repeat tremolo ");
            append(MathUtil.truncate(MathUtil.divide(note.getDuration(), MathUtil.divide(PartBuilder.CURRENT_ATTRIBUTES.getDivisions(), MathUtil.exp(MathUtil.newBigDecimal(2), tremolo.getTremoloMarks())))));
            append(" { ");
        }

        Stem stem = note.getStem();
        if(stem != null) {
            StemType stemType = stem.getValue();
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
            NoteheadType noteheadType = notehead.getValue();
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
            Connection connectionType = slur.getType();
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
        if (tremolo != null && tremolo.getType() == TremoloType.STOP) {
            append(" } ");
        }

        return stringBuilder;
    }

    private StringBuilder noteDurationBuild() throws BuildException {
        NoteType noteType = note.getNoteType();
        BigDecimal duration = note.getDuration();
        Tremolo tremolo = note.getTremolo();
        try {
            if (tremolo != null && (tremolo.getType() == TremoloType.START || tremolo.getType() == TremoloType.STOP)) {
                append(MathUtil.truncate(MathUtil.exp(MathUtil.newBigDecimal(2), tremolo.getTremoloMarks() + 2)));
            } else if (noteType instanceof Rest && TypeUtil.getBoolean(((Rest)noteType).getMeasure())) {
                append(TimeSignatureUtil.getWholeMeasureRestRepresentation());
            } else if (note.getNoteValue() != null) {
                noteTypeValueBuild();
            } else if (MathUtil.isPositive(duration)) {
                append(TimeSignatureUtil.getDurationRepresentationValue(duration));
            }
        } catch (DurationException e) {
            e.setUnhandledDuration(duration);
            throw e;
        }

        return stringBuilder;
    }

    private StringBuilder noteTypeValueBuild() {
        NoteTypeValue noteTypeValue = note.getNoteValue();
        if (noteTypeValue == null) return stringBuilder;
        append(NoteUtil.getNoteTypeValue(noteTypeValue));
        note.getDots().forEach(dot -> append("."));

        return stringBuilder;
    }

    private StringBuilder startGraceBuild() {
        if (note.isGraceNote()) {
            if (note.getGrace().getGraceType() == OrderedGroup.FIRST || note.getGrace().getGraceType() == OrderedGroup.SINGLETON) {
                if(TypeUtil.getBoolean(note.getGrace().getSlash())) {
                    append("\\slashedGrace ");
                } else {
                    append("\\grace ");
                }
            }
            if(note.getGrace().getGraceType() == OrderedGroup.FIRST) {
                append("{ ");
            }
        }

        return stringBuilder;
    }

    private StringBuilder finishGraceBuild() {
        if(note.isGraceNote() && note.getGrace().getGraceType() == OrderedGroup.LAST) {
            append(" }");
        }

        return stringBuilder;
    }

    private StringBuilder notationsBuild(List<Notations> notationsList) throws BuildException {
        // Tied executed only once, unless it's a let-ring
        boolean tiedExecuted = false;
        for(Notations notations : notationsList) {
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
                } else if (notation instanceof Fermata) {
                    if (note != null) {
                        NoteType noteType = note.getNoteType();
                        if (noteType != null) {
                            if (noteType instanceof Rest) {
                                Rest rest = (Rest)noteType;
                                if (TypeUtil.getBoolean(rest.getMeasure())) {
                                    Fermata fermata = (Fermata)notation;
                                    fermata.setMarkup(true);
                                }
                            }
                        }
                    }
                }

                boolean isExecutableTiedNotation = notation instanceof Tied && ((Tied)notation).getType() != TiedType.LET_RING;
                if (isExecutableTiedNotation && tiedExecuted) continue;

                MusicDataBuilder musicDataBuilder = new MusicDataBuilder(notation);
                append(musicDataBuilder.build().toString());

                if(isExecutableTiedNotation) tiedExecuted = true;
            }
        }

        return stringBuilder;
    }

    private StringBuilder beamBuild() {
        List<Beam> beams = note.getBeams();
        for (Beam beam : beams) {
            Integer beamNumber = beam.getNumber();
            BeamType beamType = beam.getValue();
            switch (beamType) {
                case BEGIN:
                    if(CURRENT_BEAMS.isEmpty()) {
                        append("[");
                    }
                    CURRENT_BEAMS.add(beamNumber);
                    break;
                case END:
                    CURRENT_BEAMS.remove(beamNumber);
                    if(CURRENT_BEAMS.isEmpty()) {
                        append("]");
                    }
                    break;
                case NON_BEAMED:
                    System.err.println("Warning: non-beamed note encountered during beam.  Skipping beam.");
                    break;
            }
        }

        return stringBuilder;
    }

    private StringBuilder preDirectionBuild() {
        if (hasMultipleDirections()) {
            if (note != null && !MathUtil.isPositive(note.getDuration())) {
                System.err.println("Warning: multiple directions on zero-duration note.  Skipping directions.");
                directions.clear();
                return stringBuilder;
            }

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
                append(TimeSignatureUtil.getSpacerRepresentation(spacerDuration));
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
                // if split direction type is Words, set it as a text mark
                if (directionType instanceof Words) ((Words)directionType).setTextMark(true);
                String directionTypeBuild = new MusicDataBuilder(directionType).build().toString();
                if (StringUtil.isNotEmpty(directionTypeBuild)) {
                    append(PlacementBuildUtil.getPlacement(direction.getDisplay()));
                    append(directionTypeBuild);
                    append(" ");
                }
            } catch (DurationException e) {
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
        return build();
    }

    public StringBuilder buildChord(Chord chord) throws BuildException {
        List<Note> notes = chord.getNotes();

        Note nonPrintedNote = notes.stream().filter(Note::isNotPrinted).findFirst().orElse(null);
        if (nonPrintedNote != null) {
            NoteBuilder nonPrintedNoteBuilder = new NoteBuilder(nonPrintedNote);
            nonPrintedNoteBuilder.noteTypeBuild();
            nonPrintedNoteBuilder.noteDurationBuild();
            append(nonPrintedNoteBuilder.stringBuilder.toString());

            return stringBuilder;
        }

        List<NoteBuilder> noteBuilders = new ArrayList<>();

        for(Note note : notes) {
            NoteBuilder noteBuilder = new NoteBuilder(note);
            noteBuilders.add(noteBuilder);

            transferDirections(note);

            List<Notations> notationsList = note.getNotationsList();
            chord.getNotationsList().addAll(notationsList);
            notationsList.clear();
        }
        directions.addAll(chord.getDirections());

        for (NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            Note note = noteBuilder.getNote();
            OrderedGroup chordType = note.getChordType();
            if (chordType == OrderedGroup.FIRST || chordType == OrderedGroup.SINGLETON) {
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
            OrderedGroup chordType = note.getChordType();
            if (chordType == OrderedGroup.SINGLETON || chordType == OrderedGroup.LAST) {
                append(noteBuilder.noteDurationBuild().toString());
            }
        }

        for (NoteBuilder noteBuilder : noteBuilders) {
            noteBuilder.clear();
            StringBuilder beamStringBuilder = noteBuilder.beamBuild();
            if (beamStringBuilder.length() > 0) {
                append(beamStringBuilder.toString());
                break;
            }
        }

        notationsBuild(chord.getNotationsList());

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
                OrderedGroup chordType = note.getChordType();
                if (chordType == OrderedGroup.FIRST || chordType == OrderedGroup.SINGLETON) {
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

        if(!TypeUtil.getBoolean(startTuplet.getBracket())) {
            append (" \\once \\override TupletBracket.bracket-visibility = ##f");
        }

        Display startTupletDisplay = startTuplet.getDisplay();
        if (startTupletDisplay != null) {
            Placement startTupletPlacement = startTupletDisplay.getPlacement();
            if(startTupletPlacement == Placement.ABOVE) {
                append(" \\tupletUp");
            } else if(startTupletPlacement == Placement.BELOW) {
                append(" \\tupletDown");
            }
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
            append(TimeSignatureUtil.getSpacerRepresentation(duration));
        } catch (DurationException e) {
            List<String> spacerRepresentations = TimeSignatureUtil.getDurationRepresentationValues(duration);
            for (String spacerRepresentation : spacerRepresentations) {
                append(" s");
                append(spacerRepresentation);
            }
        }

        return stringBuilder;
    }

    private boolean hasMultipleDirections() {
        Set<String> multipleDirectionTypesPresent = new HashSet<>();
        for (Direction direction : directions) {
            for (DirectionTypeList directionTypeList : direction.getDirectionTypeLists()) {
                for (DirectionType directionType : directionTypeList.getDirectionTypes()) {
                    String name = directionType.getClass().getSimpleName();
                    if (DirectionType.MULTIPLE_DIRECTION_TYPES.contains(name)) {
                        if (multipleDirectionTypesPresent.contains(name)) return true;
                        multipleDirectionTypesPresent.add(name);
                    }
                }
            }
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

    private void transferDirections(Note note) {
        List<Direction> noteDirections = note.getDirections();
        directions.addAll(noteDirections);
        noteDirections.clear();
    }
}
