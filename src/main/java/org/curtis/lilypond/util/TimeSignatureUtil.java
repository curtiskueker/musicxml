package org.curtis.lilypond.util;

import org.curtis.musicxml.attributes.TimeSignature;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TimeSignatureUtil {
    private TimeSignatureUtil() {

    }

    public static String getWholeMeasureRepresentation(TimeSignature timeSignature) {
        return getWholeMeasureRepresentation(timeSignature.getBeats(), timeSignature.getBeatType());
    }

    public static String getWholeMeasureRepresentation(String numerator, String denominator) {
        BigDecimal numeratorValue = MathUtil.newBigDecimal(numerator);
        BigDecimal denominatorValue = MathUtil.newBigDecimal(denominator);

        return getWholeMeasureRepresentation(numeratorValue, denominatorValue);
    }

    public static String getWholeMeasureRepresentation(BigDecimal numerator, BigDecimal denominator) {
        BigDecimal totalBeats = getTotalBeats(numerator, denominator);
        BigDecimal representationValue = MathUtil.divide(MathUtil.newBigDecimal(4), totalBeats);

        int loopCount = 0;
        while(!rounds(representationValue)) {
            loopCount++;
            representationValue = MathUtil.multiply(representationValue, MathUtil.newBigDecimal(1.5));
            if(loopCount >= 5) break;
        }

        String wholeMeasureNoteRepresentation = String.valueOf(representationValue.setScale(0, RoundingMode.HALF_UP).intValueExact());
        for(int i = 1; i <= loopCount; i++) {
            wholeMeasureNoteRepresentation += ".";
        }

        return wholeMeasureNoteRepresentation;
    }

    private static boolean rounds(BigDecimal value) {
        BigDecimal comparisonValue = new BigDecimal(value.toString()).setScale(0, RoundingMode.HALF_UP);
        return MathUtil.isNegative(MathUtil.subtract(MathUtil.subtract(value, comparisonValue).abs(), MathUtil.newBigDecimal(.1)));
    }

    public static BigDecimal getTotalBeats(String numerator, String denominator) {
        BigDecimal numeratorValue = MathUtil.newBigDecimal(numerator);
        BigDecimal denominatorValue = MathUtil.newBigDecimal(denominator);

        return getTotalBeats(numeratorValue, denominatorValue);
    }

    public static BigDecimal getTotalBeats(BigDecimal numerator, BigDecimal denominator) {
        // Calculates number of quarter note beats in a measure
        return MathUtil.divide(MathUtil.multiply(MathUtil.newBigDecimal(4), numerator), denominator);
    }
}
