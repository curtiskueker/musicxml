package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.musicdata.MusicDataBuilder;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.lyric.Extend;
import org.curtis.musicxml.note.lyric.Lyric;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Part;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LyricPartBuilder extends FilteredPartBuilder {
    private Part part;
    private int currentLyricCount = 1;
    private List<List<Lyric>> lyricLists;
    private int lyricsCounter = 1;

    public LyricPartBuilder(Part part) {
        this.part = part;
    }

    public StringBuilder build() throws BuildException {
        append("\\new Lyrics = \"Lyrics");
        append(String.valueOf(lyricsCounter));
        append("\" ");
        appendLine("\\lyricmode {");

        lyricsCounter++;

        // initialize one lyric list
        lyricLists = new ArrayList<>();
        lyricLists.add(new ArrayList<>());

        for (Measure measure : part.getMeasures()) {
            for (MusicData musicData : measure.getMusicDataList()) {
                adjustCurrentDuration(musicData);

                if (musicData instanceof Note) {
                    Note note = (Note)musicData;
                    BigDecimal totalBeats = MathUtil.divide(note.getDuration(), currentDivisions);
                    List<Lyric> lyrics = note.getLyrics();
                    if (lyrics.isEmpty()) {
                        if (lyricLists.size() == 1) {
                            Lyric emptyLyric = newEmptyLyric();
                            emptyLyric.setTotalBeats(totalBeats);
                            MusicDataBuilder musicDataBuilder = new MusicDataBuilder(emptyLyric);
                            append(musicDataBuilder.build().toString());
                        } else if (lyricLists.size() > 1) {
                            for(List<Lyric> lyricList : lyricLists) {
                                Lyric emptyLyric = newEmptyLyric();
                                emptyLyric.setTotalBeats(totalBeats);
                                lyricList.add(emptyLyric);
                            }
                        }
                    } else {
                        int lyricCount = lyrics.size();
                        if(lyricCount != currentLyricCount) {
                            if (currentLyricCount > 1) {
                                buildLyricLists();
                            }

                            lyricLists = new ArrayList<>(lyricCount);
                            for(int i = 1; i <= lyricCount; i++) {
                                lyricLists.add(new ArrayList<>());
                            }
                        }

                        if (lyricCount == 1) {
                            Lyric lyric = lyrics.get(0);
                            lyric.setTotalBeats(totalBeats);
                            MusicDataBuilder musicDataBuilder = new MusicDataBuilder(lyric);
                            append(musicDataBuilder.build().toString());
                        } else if (lyricCount > 1) {
                            for(Lyric lyric : lyrics) {
                                Integer lyricNumber = StringUtil.getInteger(lyric.getNumber());
                                if (lyricNumber ==  null) throw new BuildException("Invalid lyric number: " + lyric.getNumber());
                                if (lyricNumber > lyricCount) throw new BuildException("Expecting consecutive numbered lyric numbers: " + lyric.getNumber());
                                lyric.setTotalBeats(totalBeats);
                                lyricLists.get(lyricNumber - 1).add(lyric);
                            }
                        }

                        currentLyricCount = lyricCount;
                    }
                }
            }
        }

        if(currentLyricCount > 1) {
            buildLyricLists();
        }

        appendLine("");
        appendLine("}");

        return stringBuilder;
    }

    private void buildLyricLists() throws BuildException {
        appendLine("<<");

        for(int i = 0; i < currentLyricCount; i++) {
            if(i > 0) {
                append("\\new Lyrics = \"Lyrics");
                append(String.valueOf(lyricsCounter));
                appendLine("\"");
                append("\\with { alignBelowContext = #\"Lyrics");
                append(String.valueOf(lyricsCounter - 1));
                appendLine("\" }");

                lyricsCounter++;
            }

            appendLine("{");

            if (i > 0) {
                append("\\set associatedVoice = \"");
                append(part.getId());
                appendLine("\"");
            }

            List<Lyric> lyrics = lyricLists.get(i);
            for(Lyric lyric : lyrics) {
                MusicDataBuilder musicDataBuilder = new MusicDataBuilder(lyric);
                append(musicDataBuilder.build().toString());
            }

            appendLine("}");
        }

        appendLine(">>");
    }

    private Lyric newEmptyLyric() {
        Lyric lyric = new Lyric();
        lyric.setLyricItem(new Extend());

        return lyric;
    }
}
