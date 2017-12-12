package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.TextDecoration;
import org.curtis.musicxml.handler.util.PlacementUtil;
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
        TextData textData = new TextData();
        textData.setValue(XmlUtil.getElementText(element));
        textData.setFont(FormattingFactory.newFont(element));
        textData.setColor(element.getAttribute("color"));
        textData.setTextDecoration(newTextDecoration(element));
        textData.setTextRotation(MathUtil.newBigDecimal(element.getAttribute("rotation")));
        textData.setLetterSpacing(element.getAttribute("letter-spacing"));
        textData.setLang(element.getAttribute("lang"));
        textData.setTextDirection(PlacementUtil.getLocation(element.getAttribute("dir")));

        return textData;
    }

    public static TextDecoration newTextDecoration(Element element) {
        TextDecoration textDecoration = new TextDecoration();
        textDecoration.setUnderline(StringUtil.getInteger(element.getAttribute("underline")));
        textDecoration.setOverline(StringUtil.getInteger(element.getAttribute("overline")));
        textDecoration.setLineThrough(StringUtil.getInteger(element.getAttribute("line-through")));

        return textDecoration;
    }

    public static Extend newExtend(Element element) {
        Extend extend = new Extend();
        extend.setType(PlacementUtil.getConnection(element.getAttribute("type")));
        extend.setPrintStyle(FormattingFactory.newPrintStyle(element));

        return extend;
    }
}
