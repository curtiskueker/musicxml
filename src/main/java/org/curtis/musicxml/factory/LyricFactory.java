package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.Connection;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.TextDecoration;
import org.curtis.musicxml.note.lyric.Extend;
import org.curtis.musicxml.note.lyric.TextData;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

public class LyricFactory {
    private LyricFactory() {

    }

    public static TextData newTextData(Element element) {
        if(element == null) return null;

        TextData textData = new TextData();
        textData.setValue(XmlUtil.getElementText(element));
        textData.setFont(FormattingFactory.newFont(element));
        textData.setColor(element.getAttribute("color"));
        textData.setTextDecoration(newTextDecoration(element));
        textData.setTextRotation(MathUtil.newBigDecimal(element.getAttribute("rotation")));
        textData.setLetterSpacing(element.getAttribute("letter-spacing"));
        textData.setLang(element.getAttribute("xml:lang"));
        textData.setTextDirection((Location)FactoryUtil.enumValue(Location.class, element.getAttribute("dir")));

        return textData;
    }

    public static TextDecoration newTextDecoration(Element element) {
        if(element == null) return null;

        Integer underline = StringUtil.getInteger(element.getAttribute("underline"));
        Integer overline = StringUtil.getInteger(element.getAttribute("overline"));
        Integer lineThrough = StringUtil.getInteger(element.getAttribute("line-through"));

        if (underline == null && overline == null && lineThrough == null) return null;

        TextDecoration textDecoration = new TextDecoration();
        textDecoration.setUnderline(underline);
        textDecoration.setOverline(overline);
        textDecoration.setLineThrough(lineThrough);

        return textDecoration;
    }

    public static Extend newExtend(Element element) {
        if(element == null) return null;

        Extend extend = new Extend();
        extend.setType((Connection) FactoryUtil.enumValue(Connection.class, element.getAttribute("type")));
        extend.setPosition(PlacementFactory.newPosition(element));
        extend.setColor(element.getAttribute("color"));

        return extend;
    }
}
