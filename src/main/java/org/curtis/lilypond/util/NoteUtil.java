package org.curtis.lilypond.util;

import org.curtis.musicxml.note.NoteTypeValue;
import org.curtis.musicxml.note.Step;
import org.curtis.util.MathUtil;

import java.math.BigDecimal;

public class NoteUtil {
    private NoteUtil() {

    }

    public static String getNoteTypeValue(NoteTypeValue noteTypeValue) {
        if(noteTypeValue == null) return "";

        switch (noteTypeValue) {
            case _1024TH:
                return "1024";
            case _512TH:
                return "512";
            case _256TH:
                return "256";
            case _128TH:
                return "128";
            case _64TH:
                return "64";
            case _32ND:
                return "32";
            case _16TH:
                return "16";
            case EIGHTH:
                return "8";
            case QUARTER:
                return "4";
            case HALF:
                return "2";
            case WHOLE:
                return "1";
            case BREVE:
                return "\\breve";
            case LONG:
                return "\\longa";
            case MAXIMA:
                return "\\maxima";
            default:
                return "";
        }
    }

    public static String getStep(Step step) {
        if(step == null) return "s";

        switch (step) {
            case A:
                return "a";
            case B:
                return "b";
            case C:
                return "c";
            case D:
                return "d";
            case E:
                return "e";
            case F:
                return "f";
            case G:
                return "g";
            default:
                return "";
        }
    }

    public static String getAlter(BigDecimal alter) {
        if (alter == null) return "";

        if(MathUtil.equalTo(alter, MathUtil.newBigDecimal(-2))) {
            return "eses";
        } else if(MathUtil.equalTo(alter, MathUtil.newBigDecimal(-1))) {
            return "es";
        } else if(MathUtil.equalTo(alter, MathUtil.newBigDecimal(1))) {
            return "is";
        } else if(MathUtil.equalTo(alter, MathUtil.newBigDecimal(2))) {
            return "isis";
        }

        return "";
    }
}
