package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.lilypond.exception.DurationException;
import org.curtis.lilypond.util.TimeSignatureUtil;
import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.display.TextFormat;
import org.curtis.musicxml.note.TimeModification;
import org.curtis.musicxml.note.lyric.Extend;
import org.curtis.musicxml.note.lyric.Lyric;
import org.curtis.musicxml.note.lyric.LyricItem;
import org.curtis.musicxml.note.lyric.LyricSyllable;
import org.curtis.musicxml.note.lyric.LyricText;
import org.curtis.musicxml.note.lyric.LyricTextData;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;

public class LyricBuilder extends MusicDataBuilder {
    public StringBuilder buildLyric(Lyric lyric) throws BuildException {
        if(lyric == null) return stringBuilder;

        BigDecimal totalBeats = lyric.getTotalBeats();
        if (!MathUtil.isPositive(totalBeats)) return stringBuilder;

        // adjust total beats
        TimeModification timeModification = lyric.getTimeModification();
        if (timeModification != null) {
            totalBeats = MathUtil.multiply(totalBeats, MathUtil.newBigDecimal(timeModification.getActualNotes()));
            totalBeats = MathUtil.divide(totalBeats, MathUtil.newBigDecimal(timeModification.getNormalNotes()));
        }

        String totalBeatRepresentation;
        try {
            totalBeatRepresentation = TimeSignatureUtil.getRepresentationValue(totalBeats);
        } catch (DurationException e) {
            throw new BuildException(e.getMessage());
        }

        Connection tupletConnection = lyric.getTuplet() == null ? null : lyric.getTuplet().getType();

        if (tupletConnection == Connection.START) {
            if (timeModification == null) throw new BuildException("Tuplet found without time modification");
            append("\\tuplet ");
            append(String.valueOf(timeModification.getActualNotes()));
            append("/");
            append(String.valueOf(timeModification.getNormalNotes()));
            append(" { ");
        }

        LyricItem lyricItem = lyric.getLyricItem();
        if(lyricItem instanceof LyricText) {
            LyricText lyricText = (LyricText)lyricItem;
            for(LyricSyllable lyricSyllable : lyricText.getLyricSyllables()) {
                LyricTextData lyricTextData = lyricSyllable.getText();
                append("\"");
                TextFormat lyricTextFormat = lyricTextData.getTextFormat();
                if (lyricTextFormat != null) append(lyricTextFormat.getValue());
                append("\"");

                append(totalBeatRepresentation);

                Connection syllabic = lyricSyllable.getSyllabic();
                if(syllabic != null) {
                    switch (syllabic) {
                        case BEGIN:
                        case MIDDLE:
                            append(" --");
                            break;
                        case SINGLE:
                    }
                }
            }
        } else if (lyricItem instanceof Extend) {
            append("\" \"");
            append(totalBeatRepresentation);
        }

        if (tupletConnection == Connection.STOP) {
            append(" } ");
        }

        append(" ");

        return stringBuilder;
    }
}
