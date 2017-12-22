package org.curtis.lilypond.util;

import org.curtis.musicxml.note.NoteTypeValue;

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
}
