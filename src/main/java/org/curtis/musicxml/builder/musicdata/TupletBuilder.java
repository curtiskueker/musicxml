package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.note.ChordNotes;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.TupletNotes;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.score.MusicData;

import java.util.List;

public class TupletBuilder extends MusicDataBuilder {
    private TupletNotes tupletNotes;

    public TupletBuilder(TupletNotes tupletNotes) {
        super(tupletNotes);
        this.tupletNotes = tupletNotes;
    }

    public StringBuilder build() {
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
            } else if(musicData instanceof ChordNotes) {
                ChordNotes chordNotes = (ChordNotes)musicData;
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

        if(!startTuplet.getBracket()) {
            append (" \\override TupletBracket.bracket-visibility = ##f");
        }

        if(startTuplet.getPlacement() == Location.ABOVE) {
            append(" \\tupletUp");
        } else if(startTuplet.getPlacement() == Location.BELOW) {
            append(" \\tupletDown");
        }

        append(" \\tuplet");

        // tuplet fraction: value is between 1 and 2
        // dependent on time-modification subelements actual-notes and normal-notes
        TimeModification timeModification = startNote.getTimeModification();
        append(" ");
        append(String.valueOf(timeModification.getActualNotes()));
        append("/");
        append(String.valueOf(timeModification.getNormalNotes()));

        append(" {");

        for(MusicData musicData : musicDataList) {
            if(musicData instanceof Direction) {
                Direction direction = (Direction)musicData;
                DirectionBuilder directionBuilder = new DirectionBuilder(direction);
                append(directionBuilder.build().toString());
            } else if(musicData instanceof Note) {
                Note note = (Note)musicData;
                NoteBuilder noteBuilder = new NoteBuilder(note);
                noteBuilder.setValues(getCurrentTimeSignature());
                append(noteBuilder.build().toString());
            } else if(musicData instanceof ChordNotes) {
                ChordNotes chordNotes = (ChordNotes)musicData;
                ChordBuilder chordBuilder = new ChordBuilder(chordNotes);
                chordBuilder.setValues(getCurrentTimeSignature());
                append(chordBuilder.build().toString());
            }
        }

        append(" }");

        if(!startTuplet.getBracket()) {
            append (" \\override TupletBracket.bracket-visibility = ##t");
        }

        return stringBuilder;
    }
}
