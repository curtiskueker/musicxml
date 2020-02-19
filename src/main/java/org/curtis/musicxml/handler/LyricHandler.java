package org.curtis.musicxml.handler;

import org.curtis.musicxml.display.Halign;
import org.curtis.musicxml.factory.DisplayFactory;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.LyricFactory;
import org.curtis.musicxml.note.lyric.Elision;
import org.curtis.musicxml.note.lyric.Humming;
import org.curtis.musicxml.note.lyric.Laughing;
import org.curtis.musicxml.note.lyric.Lyric;
import org.curtis.musicxml.note.lyric.LyricItem;
import org.curtis.musicxml.note.lyric.LyricSyllable;
import org.curtis.musicxml.note.lyric.LyricText;
import org.curtis.musicxml.note.lyric.Syllabic;
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
                                Elision elision = new Elision();
                                elision.setValue(XmlUtil.getElementText(textElement));
                                elision.setDisplay(DisplayFactory.newDisplay(textElement));
                                elision.setSmufl(textElement.getAttribute("smufl"));
                                lyricSyllable.setElision(elision);
                                break;
                            case "syllabic":
                                lyricSyllable.setSyllabic(FactoryUtil.enumValue(Syllabic.class, XmlUtil.getElementText(textElement)));
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
                lyricItem = LyricFactory.newExtend(lyricItemElement);
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
        lyric.setJustify(FactoryUtil.enumValue(Halign.class, element.getAttribute("justify")));
        lyric.setDisplay(DisplayFactory.newDisplay(element));
        lyric.setPrintObject(FormattingFactory.getPrintObject(element));
        lyric.setTimeOnly(element.getAttribute("time-only"));
        lyrics.add(lyric);
    }
}
