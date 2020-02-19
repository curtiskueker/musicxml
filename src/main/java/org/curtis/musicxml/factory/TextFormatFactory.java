package org.curtis.musicxml.factory;

import org.curtis.musicxml.display.EnclosureShape;
import org.curtis.musicxml.display.Halign;
import org.curtis.musicxml.display.TextDirection;
import org.curtis.musicxml.display.TextFormat;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.math.BigDecimal;

public class TextFormatFactory {
    public TextFormatFactory() {

    }

    public static TextFormat newTextFormat(Element element) {
        if (element == null) return null;

        Halign justify = FactoryUtil.enumValue(Halign.class, element.getAttribute("justify"));
        Integer underline = StringUtil.getInteger(element.getAttribute("underline"));
        Integer overline = StringUtil.getInteger(element.getAttribute("overline"));
        Integer lineThrough = StringUtil.getInteger(element.getAttribute("line-through"));
        BigDecimal textRotation = MathUtil.newBigDecimal(element.getAttribute("rotation"));
        String letterSpacing = element.getAttribute("letter-spacing");
        String lineHeight = element.getAttribute("line-height");
        String lang = element.getAttribute("xml:lang");
        String space = element.getAttribute("xml:space");
        TextDirection textDirection = FactoryUtil.enumValue(TextDirection.class, element.getAttribute("dir"));
        EnclosureShape enclosureShape = FactoryUtil.enumValue(EnclosureShape.class, element.getAttribute("enclosure"));
        String value = XmlUtil.getElementText(element);

        if (justify == null && underline == null && overline == null && lineThrough == null && textRotation == null &&
                StringUtil.isEmpty(letterSpacing) && StringUtil.isEmpty(lineHeight) &&
                StringUtil.isEmpty(lang) && StringUtil.isEmpty(space) && textDirection == null && enclosureShape == null && StringUtil.isEmpty(value))
            return null;

        TextFormat textFormat = new TextFormat();
        textFormat.setValue(value);
        textFormat.setJustify(justify);
        textFormat.setUnderline(underline);
        textFormat.setOverline(overline);
        textFormat.setLineThrough(lineThrough);
        textFormat.setTextRotation(textRotation);
        textFormat.setLetterSpacing(letterSpacing);
        textFormat.setLineHeight(lineHeight);
        textFormat.setLang(lang);
        textFormat.setSpace(space);
        textFormat.setTextDirection(textDirection);
        textFormat.setEnclosure(enclosureShape);

        return textFormat;
    }
}
