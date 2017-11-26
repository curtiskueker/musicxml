package org.curtis.musicxml.builder.musicdata;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.TupletNotes;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.score.MusicData;

import java.util.List;

public class TupletBuilder extends MusicDataBuilder {
    private TupletNotes tupletNotes;
    private static int count = 1;
    private Integer noteCount = 0;

    public TupletBuilder(TupletNotes tupletNotes) {
        super(tupletNotes);
        this.tupletNotes = tupletNotes;
    }

    public StringBuilder build() {
        List<MusicData> musicDataList = tupletNotes.getMusicDataList();
        if(musicDataList == null || musicDataList.isEmpty() || musicDataList.size() < 2) {
            return stringBuilder;
        }

        // TODO: tuplets with chords
        Tuplet startTuplet = null;
        for(MusicData musicData : musicDataList) {
            if(musicData instanceof Note) {
                noteCount++;
                Note note = (Note)musicData;
                startTuplet = note.getTuplet();
            }
        }
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

        // tuplet fraction: value is between 1 and two
        Integer numerator = musicDataList.size();
        append(" ");
        append(String.valueOf(numerator));
        append("/");
        // denominator is greatest power of 2 less than numerator
        // TODO: values taken from duration
        int result = 1;
        for(int i = 0; i < 12; i++) {
            int current = 1 << i;
            if (current > numerator) break;

            result = current;
        }
        append(String.valueOf(result));

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
            }
        }

        append(" }");

        if(!startTuplet.getBracket()) {
            append (" \\override TupletBracket.bracket-visibility = ##t");
        }

        return stringBuilder;
    }
}
