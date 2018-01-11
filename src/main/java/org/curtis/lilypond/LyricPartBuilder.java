package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.exception.TimeSignatureException;
import org.curtis.lilypond.musicdata.MusicDataBuilder;
import org.curtis.lilypond.util.TimeSignatureUtil;
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

        // pre-process lyric durations
        for (Measure measure : part.getMeasures()) {
            for (MusicData musicData : measure.getMusicDataList()) {
                adjustCurrentDuration(musicData);

                if (musicData instanceof Note) {
                    Note note = (Note) musicData;
                    List<Lyric> lyrics = note.getLyrics();
                    BigDecimal totalBeats = MathUtil.divide(currentDuration, currentDivisions);

                    if (!lyrics.isEmpty()) {
                        List<Lyric> additionalLyrics = new ArrayList<>();
                        for (Lyric lyric : lyrics) {
                            BigDecimal currentMeasureBeats;
                            try {
                                currentMeasureBeats = TimeSignatureUtil.getCurrentMeasureBeats();
                            } catch (TimeSignatureException e) {
                                throw new BuildException(e.getMessage());
                            }
                            if (MathUtil.largerThan(totalBeats, currentMeasureBeats)) {
                                while (MathUtil.isPositive(totalBeats)) {
                                    if (MathUtil.largerThan(totalBeats, currentMeasureBeats)) {
                                        Lyric additionalLyric = newEmptyLyric();
                                        additionalLyric.setNumber(lyric.getNumber());
                                        additionalLyric.setTotalBeats(currentMeasureBeats);
                                        additionalLyrics.add(additionalLyric);
                                        totalBeats = MathUtil.subtract(totalBeats, currentMeasureBeats);
                                    } else {
                                        lyric.setTotalBeats(totalBeats);
                                        totalBeats = MathUtil.ZERO;
                                    }
                                }
                            } else {
                                lyric.setTotalBeats(totalBeats);
                            }
                        }
                        lyrics.addAll(additionalLyrics);
                    } else {
                        Lyric lyric = newEmptyLyric();
                        lyric.setTotalBeats(totalBeats);
                        lyrics.add(lyric);
                    }

                    currentDuration = MathUtil.ZERO;
                }
            }
        }

        for (Measure measure : part.getMeasures()) {
            for (MusicData musicData : measure.getMusicDataList()) {
                if (musicData instanceof Note) {
                    Note note = (Note)musicData;
                    List<Lyric> lyrics = note.getLyrics();
                    if (!lyrics.isEmpty()) {
                        int lyricCount = lyrics.size();
                        if(lyricCount != currentLyricCount) {
                            if (currentLyricCount > 1) {
                                buildLyricLists();
                            }
                            if(lyricCount > 1) {
                                lyricLists = new ArrayList<>(lyricCount);
                                for(int i = 1; i <= lyricCount; i++) {
                                    lyricLists.add(new ArrayList<>());
                                }
                            }
                        }

                        if (lyricCount == 1) {
                            MusicDataBuilder musicDataBuilder = new MusicDataBuilder(lyrics.get(0));
                            append(musicDataBuilder.build().toString());
                        } else if (lyricCount > 1) {
                            for(Lyric lyric : lyrics) {
                                Integer lyricNumber = StringUtil.getInteger(lyric.getNumber());
                                if (lyricNumber ==  null) throw new BuildException("Invalid lyric number: " + lyric.getNumber());
                                if (lyricNumber > lyricCount) throw new BuildException("Expecting consecutive numbered lyric numbers: " + lyric.getNumber());
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
