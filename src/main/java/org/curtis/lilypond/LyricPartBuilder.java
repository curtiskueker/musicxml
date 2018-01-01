package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.musicdata.MusicDataBuilder;
import org.curtis.musicxml.note.lyric.Lyric;

import java.util.List;

public class LyricPartBuilder extends AbstractBuilder {
    private List<List<Lyric>> lyrics;
    private String partId;

    public LyricPartBuilder(List<List<Lyric>> lyrics, String partId) {
        this.lyrics = lyrics;
        this.partId = partId;
    }

    public StringBuilder build() throws BuildException {
        append("\\new Lyrics \\lyricsto \"");
        append(partId);
        appendLine("\" {");

        for (List<Lyric> lyricList : lyrics) {
            for(Lyric lyric : lyricList) {
                MusicDataBuilder musicDataBuilder = new MusicDataBuilder(lyric);
                append(musicDataBuilder.build().toString());
            }
        }

        appendLine("");
        appendLine("}");

        return stringBuilder;
    }
}
