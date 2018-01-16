package org.curtis.lilypond;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.musicdata.MusicDataBuilder;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.note.lyric.Extend;
import org.curtis.musicxml.note.lyric.Lyric;
import org.curtis.musicxml.note.notation.Tuplet;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Part;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class LyricPartBuilder extends FilteredPartBuilder {
    private Part part;
    private int currentLyricCount = 1;
    private List<List<Lyric>> lyricLists;
    private static int lyricsCounter = 1;

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
                    List<Lyric> lyrics = note.getLyrics();
                    if (lyrics.isEmpty()) {
                        if (lyricLists.size() == 1) {
                            Lyric emptyLyric = newEmptyLyric();
                            setLyricValues(emptyLyric, note);
                            MusicDataBuilder musicDataBuilder = new MusicDataBuilder(emptyLyric);
                            append(musicDataBuilder.build().toString());
                        } else if (lyricLists.size() > 1) {
                            for(List<Lyric> lyricList : lyricLists) {
                                Lyric emptyLyric = newEmptyLyric();
                                setLyricValues(emptyLyric, note);
                                lyricList.add(emptyLyric);
                            }
                        }
                    } else {
                        // Lyric count is max lyric number
                        int lyricCount = 0;
                        for (Lyric lyric : lyrics) {
                            Integer lyricNumber = StringUtil.getInteger(lyric.getNumber());
                            if (lyricNumber ==  null) throw new BuildException(getExceptionStringPrefix(measure) + "Invalid lyric number: " + lyric.getNumber());
                            if (lyricNumber > lyricCount) lyricCount = lyricNumber;
                        }

                        if(lyricCount != currentLyricCount) {
                            if (lyricCount == 1 || currentLyricCount == 1) {
                                // if we move from or to 1, rebuild the lists
                                if (currentLyricCount > 1) {
                                    buildLyricLists();
                                }

                                lyricLists = new ArrayList<>(lyricCount);
                                for(int i = 1; i <= lyricCount; i++) {
                                    lyricLists.add(new ArrayList<>());
                                }
                            } else if (lyricCount > currentLyricCount) {
                                // if we're expanding the list
                                // add a new lyric list and stock it with empty lyrics
                                for (int listIndex = lyricCount; listIndex < currentLyricCount; listIndex++) {
                                    List<Lyric> lyricListToAdd = new ArrayList<>();
                                    for (Lyric lyric : lyricLists.get(0)) {
                                        Lyric lyricToAdd = newEmptyLyric();
                                        lyricToAdd.setTotalBeats(lyric.getTotalBeats());
                                        lyricListToAdd.add(lyricToAdd);
                                    }
                                    lyricLists.add(lyricListToAdd);
                                }
                            } else {
                                lyricCount = currentLyricCount;
                            }
                        }

                        if (lyricCount == 1) {
                            Lyric lyric = lyrics.get(0);
                            setLyricValues(lyric, note);
                            MusicDataBuilder musicDataBuilder = new MusicDataBuilder(lyric);
                            append(musicDataBuilder.build().toString());
                        } else if (lyricCount > 1) {
                            // get the lyric for each number in turn
                            // add an empty lyric, if not found
                            for (int lyricIndex = 1; lyricIndex <= lyricCount; lyricIndex++) {
                                Lyric lyricToAdd = null;
                                for (Lyric lyric : lyrics) {
                                    Integer lyricNumber = StringUtil.getInteger(lyric.getNumber());
                                    if (lyricIndex == lyricNumber) {
                                        lyricToAdd = lyric;
                                        break;
                                    }
                                }
                                if (lyricToAdd == null) lyricToAdd = newEmptyLyric();
                                setLyricValues(lyricToAdd, note);
                                lyricLists.get(lyricIndex - 1).add(lyricToAdd);
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

        appendLine();
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

    private void setLyricValues(Lyric lyric, Note note) {
        lyric.setTotalBeats(MathUtil.divide(note.getDuration(), currentDivisions));
        lyric.setTimeModification(note.getTimeModification());
        lyric.setTuplet(note.getTuplet());

        Tuplet tuplet = note.getTuplet();
        if (tuplet != null) lyric.setTuplet(tuplet);
    }
}
