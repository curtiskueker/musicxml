package org.curtis.lilypond.part;

import org.curtis.lilypond.AbstractBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Part;

import java.util.List;

import static org.curtis.musicxml.util.MusicXmlUtil.DEBUG;

public class PartBuilder extends AbstractBuilder {
    private Part part;
    private boolean hasLyrics = false;

    public static Attributes CURRENT_ATTRIBUTES;
    public static String CURRENT_PART_ID;

    public PartBuilder(Part part) {
        this.part = part;
        CURRENT_PART_ID = part.getPartId();
    }

    public StringBuilder build() throws BuildException {
        if (DEBUG) System.err.println("Part " + part.getPartId());
        List<Measure> measures = part.getMeasures();

        // pre-processing loop
        // check for endings
        // check for vocal part: presence of lyric is indicator
        for(Measure measure : measures) {
            List<MusicData> musicDataList = measure.getMusicDataList();
            for(MusicData musicData : musicDataList) {
                if(musicData instanceof Note) {
                    Note note = (Note)musicData;
                    if (!note.getLyrics().isEmpty()) hasLyrics = true;
                }
            }
        }

        // main processing loop
        if(hasLyrics) {
            LyricPartBuilder lyricPartBuilder = new LyricPartBuilder(part);
            append(lyricPartBuilder.build().toString());
        } else {
            VoicePartBuilder voicePartBuilder = new VoicePartBuilder(part);
            append(voicePartBuilder.build().toString());
        }

        return stringBuilder;
    }
}
