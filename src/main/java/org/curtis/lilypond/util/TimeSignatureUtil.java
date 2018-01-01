package org.curtis.lilypond.util;

import org.curtis.lilypond.PartBuilder;
import org.curtis.lilypond.exception.TimeSignatureException;
import org.curtis.musicxml.attributes.time.Time;
import org.curtis.musicxml.attributes.time.TimeSignature;
import org.curtis.musicxml.attributes.time.TimeSignatureType;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class TimeSignatureUtil {
    private TimeSignatureUtil() {

    }

    public static String getWholeMeasureRepresentation() throws TimeSignatureException {
        TimeSignatureType timeSignature = getCurrentTimeSignature(PartBuilder.CURRENT_ATTRIBUTES.getTimeList());
        if(timeSignature == null) return "";
        return getWholeMeasureRepresentation(timeSignature.getBeats(), timeSignature.getBeatType());
    }

    public static String getWholeMeasureRepresentation(String numerator, String denominator) throws TimeSignatureException {
        BigDecimal numeratorValue = MathUtil.newBigDecimal(numerator);
        BigDecimal denominatorValue = MathUtil.newBigDecimal(denominator);

        return getWholeMeasureRepresentation(numeratorValue, denominatorValue);
    }

    public static String getWholeMeasureRepresentation(BigDecimal numerator, BigDecimal denominator) throws TimeSignatureException {
        BigDecimal totalBeats = getTotalBeats(numerator, denominator);

        return getRepresentationValue(totalBeats);
    }

    public static String getRepresentationValue(BigDecimal totalBeats) throws TimeSignatureException {
        BigDecimal representationValue = MathUtil.divide(MathUtil.newBigDecimal(4), totalBeats);

        int loopCount = 0;
        while(!rounds(representationValue)) {
            loopCount++;
            representationValue = MathUtil.multiply(representationValue, MathUtil.newBigDecimal(1.5));
            if(loopCount >= 5) break;
        }

        int noteRepresentation = representationValue.setScale(0, RoundingMode.HALF_UP).intValueExact();

        // If represenetation isn't a multiple of 2, or loop count greater than one, throw an exception
        if(!((noteRepresentation & -noteRepresentation) == noteRepresentation) || loopCount > 1) {
            throw new TimeSignatureException("Invalid duration representation value");
        }


        String representationString = String.valueOf(noteRepresentation);
        for(int i = 1; i <= loopCount; i++) {
            representationString += ".";
        }

        return representationString;
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

    public static TimeSignatureType getCurrentTimeSignature(List<Time> timeList) {
        for(Time time : timeList) {
            if (time instanceof TimeSignature) {
                TimeSignature timeSignature = (TimeSignature)time;
                List<TimeSignatureType> timeSignatures = timeSignature.getTimeSignatureList();
                if(!timeSignatures.isEmpty()) {
                    return timeSignatures.get(0);
                }
            }
        }

        return null;
    }
}
