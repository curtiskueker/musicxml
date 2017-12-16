package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.lyric.Lyric;
import org.curtis.musicxml.note.lyric.LyricItem;
import org.curtis.musicxml.note.lyric.LyricSyllable;
import org.curtis.musicxml.note.lyric.TextData;

public class LyricBuilder extends MusicDataBuilder {
    public StringBuilder buildLyric(Lyric lyric) throws BuildException {
        if(lyric == null) return stringBuilder;

        LyricItem lyricItem = lyric.getLyricItem();
        if(lyricItem instanceof LyricSyllable) {
            LyricSyllable lyricSyllable = (LyricSyllable)lyricItem;

            TextData textData = lyricSyllable.getText();
            if(textData != null) {
                append(textData.getValue());
                append(" ");
            }

            Connection syllabic = lyricSyllable.getSyllabic();
            if(syllabic != null) {
                switch (syllabic) {
                    case BEGIN:
                    case MIDDLE:
                        append("-- ");
                        break;
                    case SINGLE:
                    case END:
                        appendLine("");
                        break;
                }
            }
        }

        return stringBuilder;
    }
}
