package org.curtis.musicxml.factory;

import org.curtis.musicxml.display.CssFontSize;
import org.curtis.musicxml.display.Display;
import org.curtis.musicxml.display.Font;
import org.curtis.musicxml.display.FontStyle;
import org.curtis.musicxml.display.FontWeight;
import org.curtis.musicxml.display.Footnote;
import org.curtis.musicxml.display.FormattedDisplay;
import org.curtis.musicxml.display.Halign;
import org.curtis.musicxml.display.Placement;
import org.curtis.musicxml.display.Position;
import org.curtis.musicxml.display.Valign;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.w3c.dom.Element;

import java.math.BigDecimal;

public class DisplayFactory {
    private DisplayFactory() {

    }

    public static Display newDisplay(Element element) {
        if (element == null) return null;

        Position position = newPosition(element);
        Placement placement = FactoryUtil.enumValue(Placement.class, element.getAttribute("placement"));
        Font font = newFont(element);
        String color = element.getAttribute("color");
        Halign halign = FactoryUtil.enumValue(Halign.class, element.getAttribute("halign"));
        Valign valign = FactoryUtil.enumValue(Valign.class, element.getAttribute("valign"));

        if (position == null && placement == null && font == null && StringUtil.isEmpty(color) && halign == null && valign == null) return null;

        Display display = new Display();
        display.setPosition(position);
        display.setPlacement(placement);
        display.setFont(font);
        display.setColor(color);
        display.setHalign(halign);
        display.setValign(valign);

        return display;
    }

    private static Position newPosition(Element element) {
        if(element == null) return null;

        BigDecimal defaultX = MathUtil.newBigDecimal(element.getAttribute("default-x"));
        BigDecimal defaultY = MathUtil.newBigDecimal(element.getAttribute("default-y"));
        BigDecimal relativeX = MathUtil.newBigDecimal(element.getAttribute("relative-x"));
        BigDecimal relativeY = MathUtil.newBigDecimal(element.getAttribute("relative-y"));

        if (invalidPositionValue(defaultX, "default-x")) defaultX = null;
        if (invalidPositionValue(defaultY, "default-y")) defaultY = null;
        if (invalidPositionValue(relativeX, "relative-x")) relativeX = null;
        if (invalidPositionValue(relativeY, "relative-y")) relativeY = null;

        if (defaultX == null && defaultY == null && relativeX == null && relativeY == null) return null;

        Position position = new Position();

        position.setDefaultX(defaultX);
        position.setDefaultY(defaultY);
        position.setRelativeX(relativeX);
        position.setRelativeY(relativeY);

        return position;
    }

    private static boolean invalidPositionValue(BigDecimal positionValue, String fieldName) {
        if (positionValue == null) return false;

        if (MathUtil.largerThan(positionValue.abs(), FactoryUtil.MAXIMUM_POSITION_VALUE)) {
            System.err.println("Warning: " + fieldName + " value " + positionValue + " exceeds maximum allowed value " + FactoryUtil.MAXIMUM_POSITION_VALUE + ".  Setting value to null.");
            return true;
        }

        return false;
    }

    public static Font newFont(Element element) {
        if(element == null) return null;

        String fontFamily = element.getAttribute("font-family");
        String fontStyle = element.getAttribute("font-style");
        String fontSizeValue = element.getAttribute("font-size");
        String fontWeight = element.getAttribute("font-weight");

        if (StringUtil.isEmpty(fontFamily) && StringUtil.isEmpty(fontStyle) && StringUtil.isEmpty(fontSizeValue) && StringUtil.isEmpty(fontWeight)) return null;

        Font font = new Font();
        font.setFontFamily(fontFamily);
        font.setFontStyle(FactoryUtil.enumValue(FontStyle.class, fontStyle));
        font.setFontSize(getFontSize(fontSizeValue));
        font.setFontWeight(FactoryUtil.enumValue(FontWeight.class, fontWeight));

        return font;
    }

    private static String getFontSize(String fontSize) {
        if (StringUtil.isEmpty(fontSize)) return null;

        try {
            MathUtil.newBigDecimal(fontSize);
            return fontSize;
        } catch (Exception e) {
            // use css enum
        }

        CssFontSize cssFontSize = FactoryUtil.enumValue(CssFontSize.class, fontSize);

        if (cssFontSize == null) {
            System.err.println("Invalid font size " + fontSize);
            return null;
        } else return fontSize;
    }

    public static void setFormattedDisplay(FormattedDisplay formattedDisplay, Element element) {
        formattedDisplay.setDisplay(newDisplay(element));
        formattedDisplay.setTextFormat(TextFormatFactory.newTextFormat(element));
    }

    public static void setDisplayPlacement(Display display, Placement placement){
        if (display == null) display = new Display();

        display.setPlacement(placement);
    }

    public static Footnote newFootnote(Element element) {
        if (element == null) return null;

        Footnote footnote = new Footnote();
        setFormattedDisplay(footnote, element);

        return footnote;
    }
}
