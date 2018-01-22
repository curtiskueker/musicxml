package org.curtis.lilypond.part;

import org.curtis.lilypond.AbstractBuilder;
import org.curtis.lilypond.util.AttributesUtil;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.note.Backup;
import org.curtis.musicxml.note.Forward;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class FilteredPartBuilder extends AbstractBuilder {
    protected BigDecimal currentDivisions = MathUtil.ZERO;
    protected BigDecimal currentDuration = MathUtil.ZERO;
    protected SortedSet<String> voices = new TreeSet<>();

    protected void adjustCurrentDuration(MusicData musicData) {
         if (musicData instanceof Attributes) {
            Attributes attributes = (Attributes)musicData;
            AttributesUtil.setCurrentAttributes(attributes);
            BigDecimal divisions = attributes.getDivisions();
            if (divisions != null) currentDivisions = divisions;
        } else if (musicData instanceof Note) {
            Note note = (Note)musicData;
            if (!note.getFullNote().isChord()) currentDuration = MathUtil.add(currentDuration, note.getDuration());
        } else if (musicData instanceof Backup) {
            Backup backup = (Backup)musicData;
            currentDuration = MathUtil.subtract(currentDuration, backup.getDuration());
        } else if (musicData instanceof Forward) {
            Forward forward = (Forward)musicData;
            currentDuration = MathUtil.add(currentDuration, forward.getDuration());
        }
    }
}
