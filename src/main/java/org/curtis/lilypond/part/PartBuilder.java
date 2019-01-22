package org.curtis.lilypond.part;

import org.curtis.lilypond.BaseBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.lyric.Humming;
import org.curtis.musicxml.note.lyric.Laughing;
import org.curtis.musicxml.note.lyric.Lyric;
import org.curtis.musicxml.note.lyric.LyricItem;
import org.curtis.musicxml.note.lyric.LyricSyllable;
import org.curtis.musicxml.note.lyric.LyricText;
import org.curtis.musicxml.note.lyric.TextData;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Part;
import org.curtis.util.StringUtil;

import java.util.List;

import static org.curtis.musicxml.util.MusicXmlUtil.DEBUG;

public class PartBuilder extends BaseBuilder {
    private Part part;

    public static Attributes CURRENT_ATTRIBUTES;
    public static String CURRENT_PART_ID;

    public PartBuilder(Part part) {
        this.part = part;
        CURRENT_PART_ID = part.getPartId();
    }

    public StringBuilder build() throws BuildException {
        if (DEBUG) System.err.println("Part " + part.getPartId());

        if(hasLyrics()) {
            LyricPartBuilder lyricPartBuilder = new LyricPartBuilder(part);
            append(lyricPartBuilder.build().toString());
        } else {
            VoicePartBuilder voicePartBuilder = new VoicePartBuilder(part);
            append(voicePartBuilder.build().toString());
        }

        return stringBuilder;
    }

    private boolean hasLyrics() {
        for(Measure measure : part.getMeasures()) {
            for(MusicData musicData : measure.getMusicDataList()) {
                if(musicData instanceof Note) {
                    Note note = (Note)musicData;
                    List<Lyric> lyrics = note.getLyrics();
                    for (Lyric lyric : lyrics) {
                        LyricItem lyricItem = lyric.getLyricItem();
                        if (lyricItem == null) continue;
                        if (lyricItem instanceof Laughing || lyricItem instanceof Humming) return true;
                        if (lyricItem instanceof LyricText) {
                            LyricText lyricText = (LyricText)lyricItem;
                            for (LyricSyllable lyricSyllable : lyricText.getLyricSyllables()) {
                                TextData textData = lyricSyllable.getText();
                                if (textData != null && StringUtil.isNotEmpty(textData.getValue())) return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
}
