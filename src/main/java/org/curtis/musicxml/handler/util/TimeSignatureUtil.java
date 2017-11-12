package org.curtis.musicxml.handler.util;

import org.curtis.musicxml.attributes.TimeSignature;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;

public class TimeSignatureUtil {
    private TimeSignatureUtil() {

    }

    public static String getWholeMeasureNoteRepresentation(TimeSignature timeSignature) {
        // calculate value of whole measure
        BigDecimal numerator = MathUtil.newBigDecimal(timeSignature.getBeats());
        BigDecimal denominator = MathUtil.newBigDecimal(timeSignature.getBeatType());
        BigDecimal totalBeats = MathUtil.divide(MathUtil.multiply(MathUtil.newBigDecimal(4), numerator), denominator);
        BigDecimal wholeMeasureNote = MathUtil.newBigDecimal(1);
        BigDecimal noteTest = MathUtil.newBigDecimal(1);
        while (MathUtil.smallerThan(wholeMeasureNote, totalBeats)) {
            noteTest = MathUtil.multiply(noteTest, MathUtil.newBigDecimal(2));
            if(MathUtil.largerThan(noteTest, totalBeats)) {
                break;
            }
            wholeMeasureNote = MathUtil.multiply(wholeMeasureNote, MathUtil.newBigDecimal(2));
        }

        String wholeMeasureNoteRepresentation = String.valueOf(4 / wholeMeasureNote.intValueExact());
        if(MathUtil.isPositive(MathUtil.subtract(totalBeats, wholeMeasureNote))) {
            wholeMeasureNoteRepresentation += ".";
        }

        return wholeMeasureNoteRepresentation;
    }
}
