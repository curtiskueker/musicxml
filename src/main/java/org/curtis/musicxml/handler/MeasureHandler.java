package org.curtis.musicxml.handler;

import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class MeasureHandler extends AbstractHandler {
    private List<Measure> measures;

    public MeasureHandler(Element element, List<Measure> measures) {
        super(element);
        this.measures = measures;
    }

    public StringBuilder handle() {
        Measure measure = new Measure();
        List<MusicData> musicDataList = measure.getMusicDataList();

        List<Element> measureSubelements = XmlUtil.getChildElements(getElement());

        for (Element measureSubelement : measureSubelements) {
            String elementName = measureSubelement.getTagName();
            switch (elementName) {
                case "attributes":
                    AttributesHandler attributesHandler = new AttributesHandler(measureSubelement, musicDataList);
                    attributesHandler.handle();
                    break;
                case "note":
                    break;
                case "direction":
                    break;
            }
        }

        measures.add(measure);

        return stringBuilder;
    }
}
