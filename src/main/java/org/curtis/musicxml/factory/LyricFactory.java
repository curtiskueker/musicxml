package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.note.lyric.Extend;
import org.curtis.musicxml.note.lyric.LyricTextData;
import org.w3c.dom.Element;

public class LyricFactory {
    private LyricFactory() {

    }

    public static LyricTextData newTextData(Element element) {
        if(element == null) return null;

        LyricTextData lyricTextData = new LyricTextData();
        DisplayFactory.setFormattedDisplay(lyricTextData, element);

        if (lyricTextData.getDisplay() == null && lyricTextData.getTextFormat() == null) return null;

        return lyricTextData;
    }

    public static Extend newExtend(Element element) {
        if(element == null) return null;

        Extend extend = new Extend();
        extend.setType(FactoryUtil.enumValue(Connection.class, element.getAttribute("type")));
        extend.setDisplay(DisplayFactory.newDisplay(element));

        return extend;
    }
}
