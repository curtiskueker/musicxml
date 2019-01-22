package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.LyricFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.note.lyric.Extend;
import org.curtis.musicxml.note.lyric.Humming;
import org.curtis.musicxml.note.lyric.Laughing;
import org.curtis.musicxml.note.lyric.Lyric;
import org.curtis.musicxml.note.lyric.LyricItem;
import org.curtis.musicxml.note.lyric.LyricSyllable;
import org.curtis.musicxml.note.lyric.LyricText;
import org.curtis.musicxml.note.lyric.TextFontColor;
import org.curtis.util.MathUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

public class LyricHandler extends BaseHandler {
    private List<Lyric> lyrics;

    public LyricHandler(List<Lyric> lyrics) {
        this.lyrics = lyrics;
    }

    public void handle(Element element) {
        Lyric lyric = new Lyric();

        List<Element> lyricSubelements = XmlUtil.getChildElements(element);

        if(lyricSubelements.isEmpty()) return;

        // Handle the main subelements first
        Element lyricItemElement = lyricSubelements.get(0);
        String lyricItemElementName = lyricItemElement.getTagName();
        LyricItem lyricItem = null;
        switch (lyricItemElementName) {
            case "syllabic":
            case "text":
                LyricText lyricText = new LyricText();

                // loop through child elements
                // when a "text" element is reached add the groups to the list
                List<List<Element>> textElementGroups = new ArrayList<>();
                List<Element> textElementGroupToAdd = new ArrayList<>();
                for(Element lyricSubelement: lyricSubelements) {
                    textElementGroupToAdd.add(lyricSubelement);
                    String lyricSubelementName = lyricSubelement.getTagName();
                    if(lyricSubelementName.equals("text")) {
                        textElementGroups.add(textElementGroupToAdd);
                        textElementGroupToAdd = new ArrayList<>();
                    }
                }

                // Set the lyric syllables
                for(List<Element> textElementGroup : textElementGroups) {
                    LyricSyllable lyricSyllable = new LyricSyllable();
                    for(Element textElement : textElementGroup) {
                        String textElementName = textElement.getTagName();
                        switch (textElementName) {
                            case "elision":
                                TextFontColor textFontColor = new TextFontColor();
                                textFontColor.setValue(XmlUtil.getElementText(textElement));
                                textFontColor.setFont(FormattingFactory.newFont(textElement));
                                textFontColor.setColor(textElement.getAttribute("color"));
                                textFontColor.setTextDecoration(LyricFactory.newTextDecoration(textElement));
                                textFontColor.setTextRotation(MathUtil.newBigDecimal(textElement.getAttribute("rotation")));
                                textFontColor.setLetterSpacing(textElement.getAttribute("letter-spacing"));
                                textFontColor.setLang(textElement.getAttribute("xml:lang"));
                                textFontColor.setTextDirection(PlacementUtil.getLocation(textElement.getAttribute("dir")));
                                lyricSyllable.setLyricElision(textFontColor);
                                break;
                            case "syllabic":
                                lyricSyllable.setSyllabic(PlacementUtil.getConnection(XmlUtil.getElementText(textElement)));
                                break;
                            case "text":
                                lyricSyllable.setText(LyricFactory.newTextData(textElement));
                                break;
                        }
                    }
                    lyricText.getLyricSyllables().add(lyricSyllable);
                }

                // Set the extend value
                lyricText.setExtend(LyricFactory.newExtend(XmlUtil.getChildElement(element, "extend")));

                lyricItem = lyricText;
                break;
            case "extend":
                Extend extend = new Extend();
                extend.setType(PlacementUtil.getConnection(lyricItemElement.getAttribute("type")));
                extend.setPrintStyle(FormattingFactory.newPrintStyle(lyricItemElement));
                lyricItem = extend;
                break;
            case "laughing":
                lyricItem = new Laughing();
                break;
            case "humming":
                lyricItem = new Humming();
                break;
        }
        lyric.setLyricItem(lyricItem);

        // Handle the end elements separately
        for(Element lyricSubelement : lyricSubelements) {
            String lyricSubelementName = lyricSubelement.getTagName();
            switch (lyricSubelementName) {
                case "end-line":
                    lyric.setEndLine(true);
                    break;
                case "end-paragraph":
                    lyric.setEndParagraph(true);
                    break;
            }
        }

        lyric.setEditorial(FormattingFactory.newEditorial(element));
        lyric.setNumber(element.getAttribute("number"));
        lyric.setName(element.getAttribute("name"));
        lyric.setJustify(PlacementUtil.getLocation(element.getAttribute("justify")));
        lyric.setPosition(PlacementFactory.newPosition(element));
        lyric.setPlacement(PlacementFactory.newPlacementLocation(element));
        lyric.setColor(element.getAttribute("color"));
        lyric.setPrintObject(FormattingFactory.getPrintObject(element));
        lyrics.add(lyric);
    }
}
