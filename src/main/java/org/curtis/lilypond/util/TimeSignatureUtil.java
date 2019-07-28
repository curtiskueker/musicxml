package org.curtis.lilypond.util;

import org.curtis.lilypond.exception.DurationException;
import org.curtis.lilypond.exception.TimeSignatureException;
import org.curtis.lilypond.part.PartBuilder;
import org.curtis.musicxml.attributes.time.Time;
import org.curtis.musicxml.attributes.time.TimeSignature;
import org.curtis.musicxml.attributes.time.TimeSignatureType;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TimeSignatureUtil {
    private static String[] LONG_NOTES = {"\\breve", "\\longa", "\\maxima"};

    private TimeSignatureUtil() {

    }

    public static String getWholeMeasureRestRepresentation() throws TimeSignatureException {
        TimeSignatureType timeSignature = getCurrentTimeSignature();
        if(timeSignature == null) return "";
        return "1*" + timeSignature.getBeats() + "/" + timeSignature.getBeatType();
    }

    public static String getWholeMeasureSpacerRepresentation() throws TimeSignatureException {
        TimeSignatureType timeSignature = getCurrentTimeSignature();
        if(timeSignature == null) return "";
        return "s1*" + timeSignature.getBeats() + "/" + timeSignature.getBeatType();
    }

    public static String getWholeMeasureRepresentation(BigDecimal numerator, BigDecimal denominator) throws DurationException {
        BigDecimal totalBeats = getTotalBeats(numerator, denominator);

        return getRepresentationValue(totalBeats);
    }

    public static String getRepresentationValue(BigDecimal totalBeats) throws DurationException {
        if (!MathUtil.isPositive(totalBeats)) throw new DurationException("Representation value is non-positive value");

        BigDecimal totalBeatsCopy = MathUtil.newBigDecimal(totalBeats);
        int dividerCount = 0;
        if (exceedsWholeNoteRepresentationValue(totalBeats)) {
            while (exceedsWholeNoteRepresentationValue(totalBeatsCopy)) {
                dividerCount++;
                totalBeatsCopy = MathUtil.divide(totalBeatsCopy, MathUtil.newBigDecimal(2));
            }
        }

        if (dividerCount > 3) throw new DurationException("Note beat total of " + totalBeats + " exceeds maxixmum value allowed.  Skipping note.");

        BigDecimal representationValue = MathUtil.divide(MathUtil.newBigDecimal(4), totalBeatsCopy);

        int multiplierCount = 0;
        BigDecimal multiplier = MathUtil.newBigDecimal(1);
        BigDecimal multiplierBase = MathUtil.ZERO;
        BigDecimal one = MathUtil.newBigDecimal(1);
        while(!rounds(representationValue) && multiplierCount <= 5) {
            multiplierCount++;
            multiplier = MathUtil.divide(multiplier, MathUtil.newBigDecimal(2));
            BigDecimal multiplierValue = MathUtil.multiply(representationValue, MathUtil.add(one, MathUtil.add(multiplierBase, multiplier)));
            if (rounds(multiplierValue)) {
                representationValue = multiplierValue;
                break;
            }
            multiplierBase = MathUtil.newBigDecimal(multiplier);
        }

        int noteRepresentation = representationValue.setScale(0, RoundingMode.HALF_UP).intValueExact();

        // If representation isn't a multiple of 2, or loop count greater than two, throw an exception
        if(!((noteRepresentation & -noteRepresentation) == noteRepresentation) || multiplierCount > 2) {
            throw new DurationException("Duration value not processed.  Total beats: " + totalBeats + ".");
        }


        String representationString = dividerCount > 0 ? LONG_NOTES[dividerCount - 1] : String.valueOf(noteRepresentation);
        for(int i = 1; i <= multiplierCount; i++) {
            representationString += ".";
        }

        return representationString;
    }

    private static boolean exceedsWholeNoteRepresentationValue(BigDecimal beats) {
        return !MathUtil.smallerThan(beats, MathUtil.newBigDecimal(8));
    }

    public static String getDurationRepresentationValue(BigDecimal duration) throws DurationException {
        BigDecimal totalBeats = MathUtil.divide(duration, PartBuilder.CURRENT_ATTRIBUTES.getDivisions());

        return getRepresentationValue(totalBeats);
    }

    public static List<String> getDurationRepresentationValues(BigDecimal duration) throws DurationException {
        BigDecimal durationCopy = MathUtil.newBigDecimal(duration);
        List<String> representationValues = new ArrayList<>();

        BigDecimal divisions = PartBuilder.CURRENT_ATTRIBUTES.getDivisions();
        BigDecimal minimumDivisions = MathUtil.divide(divisions, MathUtil.newBigDecimal(4));

        while (!MathUtil.smallerThan(durationCopy, minimumDivisions)) {
            if (MathUtil.smallerThan(durationCopy, divisions)) {
                divisions = MathUtil.divide(divisions, MathUtil.newBigDecimal(2));
            }
            if (!MathUtil.smallerThan(durationCopy, divisions)) {
                try {
                    representationValues.add(getDurationRepresentationValue(divisions));
                    durationCopy = MathUtil.subtract(durationCopy, divisions);
                } catch (DurationException e) {
                    // skip
                }
            }
        }

        if (MathUtil.isPositive(durationCopy)) {
            DurationException durationException = new DurationException("Unhandled duration value: " + duration);
            durationException.setUnhandledDuration(duration);
        }

        return representationValues;
    }

    private static boolean rounds(BigDecimal value) {
        BigDecimal comparisonValue = new BigDecimal(value.toString()).setScale(0, RoundingMode.HALF_UP);
        return MathUtil.isNegative(MathUtil.subtract(MathUtil.subtract(value, comparisonValue).abs(), MathUtil.newBigDecimal(.01)));
    }

    public static String getSpacerRepresentation(BigDecimal duration) throws DurationException {
        return "s" + getDurationRepresentationValue(duration);
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
        if (PartBuilder.CURRENT_ATTRIBUTES == null) return null;

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
