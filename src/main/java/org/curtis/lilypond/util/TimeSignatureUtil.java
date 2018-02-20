package org.curtis.lilypond.util;

import org.curtis.lilypond.part.PartBuilder;
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
        TimeSignatureType timeSignature = getCurrentTimeSignature();
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
        if (!MathUtil.isPositive(totalBeats)) throw new TimeSignatureException("Representation value is non-positive value");

        if (MathUtil.equalTo(totalBeats, MathUtil.newBigDecimal(8))) return "\\breve";

        BigDecimal representationValue = MathUtil.divide(MathUtil.newBigDecimal(4), totalBeats);

        int loopCount = 0;
        BigDecimal multiplier = MathUtil.newBigDecimal(1);
        BigDecimal multiplierBase = MathUtil.ZERO;
        BigDecimal one = MathUtil.newBigDecimal(1);
        while(!rounds(representationValue) && loopCount <= 5) {
            loopCount++;
            multiplier = MathUtil.divide(multiplier, MathUtil.newBigDecimal(2));
            BigDecimal multiplierValue = MathUtil.multiply(representationValue, MathUtil.add(one, MathUtil.add(multiplierBase, multiplier)));
            if (rounds(multiplierValue)) {
                representationValue = multiplierValue;
                break;
            }
            multiplierBase = MathUtil.newBigDecimal(multiplier.doubleValue());
        }

        int noteRepresentation = representationValue.setScale(0, RoundingMode.HALF_UP).intValueExact();

        // If represenetation isn't a multiple of 2, or loop count greater than two, throw an exception
        if(!((noteRepresentation & -noteRepresentation) == noteRepresentation) || loopCount > 2) {
            throw new TimeSignatureException("Invalid duration representation value.  Total beats: " + totalBeats + ".");
        }


        String representationString = String.valueOf(noteRepresentation);
        for(int i = 1; i <= loopCount; i++) {
            representationString += ".";
        }

        return representationString;
    }

    public static String getDurationRepresentationValue(BigDecimal duration) throws TimeSignatureException {
        BigDecimal totalBeats = MathUtil.divide(duration, PartBuilder.CURRENT_ATTRIBUTES.getDivisions());

        return getRepresentationValue(totalBeats);
    }

    private static boolean rounds(BigDecimal value) {
        BigDecimal comparisonValue = new BigDecimal(value.toString()).setScale(0, RoundingMode.HALF_UP);
        return MathUtil.isNegative(MathUtil.subtract(MathUtil.subtract(value, comparisonValue).abs(), MathUtil.newBigDecimal(.1)));
    }

    public static BigDecimal getCurrentMeasureBeats() throws TimeSignatureException {
        TimeSignatureType currentTimeSignature = getCurrentTimeSignature();
        if (currentTimeSignature == null) throw new TimeSignatureException("Current time signature not found");
        BigDecimal numerator = MathUtil.newBigDecimal(currentTimeSignature.getBeats());
        BigDecimal denominator = MathUtil.newBigDecimal(currentTimeSignature.getBeatType());

        return getTotalBeats(numerator, denominator);
    }

    public static BigDecimal getTotalBeats(BigDecimal numerator, BigDecimal denominator) {
        // Calculates number of quarter note beats in a measure
        return MathUtil.divide(MathUtil.multiply(MathUtil.newBigDecimal(4), numerator), denominator);
    }

    public static BigDecimal getWholeMeasureDuration() throws TimeSignatureException {
        return MathUtil.multiply(PartBuilder.CURRENT_ATTRIBUTES.getDivisions(), getCurrentMeasureBeats());
    }

    public static TimeSignatureType getCurrentTimeSignature() {
        List<Time> timeList = PartBuilder.CURRENT_ATTRIBUTES.getTimeList();
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
