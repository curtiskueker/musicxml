package org.curtis.musicxml.handler;

import org.curtis.musicxml.common.Font;
import org.curtis.musicxml.common.FontStyle;
import org.curtis.musicxml.common.FontWeight;
import org.curtis.musicxml.common.FormattedText;
import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.common.PrintStyleAlign;
import org.curtis.musicxml.common.TextFormatting;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.type.DirectionType;
import org.curtis.musicxml.direction.type.Words;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.util.EnumUtil;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class DirectionHandler extends AbstractHandler {
    private List<MusicData> musicDataList;

    public DirectionHandler(Element element, List<MusicData> musicDataList) {
        super(element);
        this.musicDataList = musicDataList;
    }

    public void handle() {
        Direction direction = new Direction();
        direction.setPlacement(EnumUtil.getLocation(getElement().getAttribute("placement")));
        if(direction.getPlacement() == null) direction.setPlacement(Location.BELOW);
        List<DirectionType> directionTypes = direction.getDirectionTypes();

        List<Element> directionSubelements = XmlUtil.getChildElements(getElement());
        for(Element directionSubelement : directionSubelements) {
            String directionElementName = directionSubelement.getTagName();
            switch (directionElementName) {
                case "direction-type":
                    List<Element> directionTypeSubelements = XmlUtil.getChildElements(directionSubelement);
                    for(Element directionTypeSubelement : directionTypeSubelements) {
                        String directionTypeElementName = directionTypeSubelement.getTagName();
                        switch (directionTypeElementName) {
                            case "words":
                                Words words = new Words();
                                FormattedText formattedText = new FormattedText();
                                formattedText.setValue(XmlUtil.getElementText(directionTypeSubelement));
                                TextFormatting textFormatting = new TextFormatting();
                                PrintStyleAlign printStyleAlign = new PrintStyleAlign();
                                PrintStyle printStyle = new PrintStyle();
                                Position position = new Position();
                                String relativeX = directionTypeSubelement.getAttribute("relative-x");
                                if(StringUtil.isNotEmpty(relativeX)) {
                                    position.setRelativeX(MathUtil.newBigDecimal(relativeX));
                                }
                                String relativeY = directionTypeSubelement.getAttribute("relative-y");
                                if(StringUtil.isNotEmpty(relativeY)) {
                                    position.setRelativeY(MathUtil.newBigDecimal(relativeY));
                                }
                                printStyle.setPosition(position);
                                Font font = new Font();
                                String fontStyle = directionTypeSubelement.getAttribute("font-style");
                                if(StringUtil.isNotEmpty(fontStyle)) {
                                    switch (fontStyle) {
                                        case "normal":
                                            font.setFontStyle(FontStyle.NORMAL);
                                            break;
                                        case "italic":
                                            font.setFontStyle(FontStyle.ITALIC);
                                            break;
                                    }
                                }
                                String fontWeight = directionTypeSubelement.getAttribute("font-weight");
                                if(StringUtil.isNotEmpty(fontWeight)) {
                                    switch (fontWeight) {
                                        case "normal":
                                            font.setFontWeight(FontWeight.NORMAL);
                                            break;
                                        case "bold":
                                            font.setFontWeight(FontWeight.BOLD);
                                            break;
                                    }
                                }
                                printStyle.setFont(font);
                                printStyleAlign.setPrintStyle(printStyle);
                                textFormatting.setPrintStyleAlign(printStyleAlign);
                                formattedText.setTextFormatting(textFormatting);
                                words.setFormattedText(formattedText);
                                directionTypes.add(words);
                                break;
                            case "wedge":
                                break;
                            case "dynamics":
                                break;
                        }
                    }
                    break;
                case "offset":
                    break;
                case "sound":
                    break;
            }
        }

        musicDataList.add(direction);
    }
}
