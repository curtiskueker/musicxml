package org.curtis.lilypond.musicdata;

import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.attributes.key.TraditionalKey;
import org.curtis.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

public class KeyBuilder extends MusicDataBuilder {
    private static final Map<Integer, String> MAJOR_KEY_MAP;
    private static final Map<Integer, String> MINOR_KEY_MAP;

    static {
        MAJOR_KEY_MAP = new HashMap<>();
        MAJOR_KEY_MAP.put(-6, "ges");
        MAJOR_KEY_MAP.put(-5, "des");
        MAJOR_KEY_MAP.put(-4, "aes");
        MAJOR_KEY_MAP.put(-3, "ees");
        MAJOR_KEY_MAP.put(-2, "bes");
        MAJOR_KEY_MAP.put(-1, "f");
        MAJOR_KEY_MAP.put(0, "c");
        MAJOR_KEY_MAP.put(1, "g");
        MAJOR_KEY_MAP.put(2, "d");
        MAJOR_KEY_MAP.put(3, "a");
        MAJOR_KEY_MAP.put(4, "e");
        MAJOR_KEY_MAP.put(5, "b");
        MAJOR_KEY_MAP.put(6, "fis");

        MINOR_KEY_MAP = new HashMap<>();
        MINOR_KEY_MAP.put(-6, "ees");
        MINOR_KEY_MAP.put(-5, "b");
        MINOR_KEY_MAP.put(-4, "f");
        MINOR_KEY_MAP.put(-3, "c");
        MINOR_KEY_MAP.put(-2, "g");
        MINOR_KEY_MAP.put(-1, "d");
        MINOR_KEY_MAP.put(0, "a");
        MINOR_KEY_MAP.put(1, "e");
        MINOR_KEY_MAP.put(2, "b");
        MINOR_KEY_MAP.put(3, "fis");
        MINOR_KEY_MAP.put(4, "cis");
        MINOR_KEY_MAP.put(5, "gis");
        MINOR_KEY_MAP.put(6, "dis");
    }

    public KeyBuilder() {

    }

    public StringBuilder buildTraditionalKey(TraditionalKey traditionalKey) throws BuildException {
        append("\\key ");

        String mode = traditionalKey.getMode();

        // default mode to major
        if (StringUtil.isEmpty(mode)) mode = "major";
        switch (mode) {
            case "major":
                append(MAJOR_KEY_MAP.get(traditionalKey.getFifths()));
                break;
            case "minor":
                append(MINOR_KEY_MAP.get(traditionalKey.getFifths()));
                break;
        }
        append(" \\");
        appendLine(mode);

        return stringBuilder;
    }
}
