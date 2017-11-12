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

    public void handle() {
        Measure measure = new Measure();
        measure.setNumber(getElement().getAttribute("number"));

        List<MusicData> musicDataList = measure.getMusicDataList();
        List<Element> measureSubelements = XmlUtil.getChildElements(getElement());

        for (Element measureSubelement : measureSubelements) {
            String elementName = measureSubelement.getTagName();
            switch (elementName) {
                case "note":
                    NoteHandler noteHandler = new NoteHandler(measureSubelement, musicDataList);
                    noteHandler.handle();
                    break;
                case "direction":
                    DirectionHandler directionHandler = new DirectionHandler(measureSubelement, musicDataList);
                    directionHandler.handle();
                    break;
                case "attributes":
                    AttributesHandler attributesHandler = new AttributesHandler(measureSubelement, musicDataList);
                    attributesHandler.handle();
                    break;
                case "barline":
                    BarlineHandler barlineHandler = new BarlineHandler(measureSubelement, musicDataList);
                    barlineHandler.handle();
                    break;
            }
        }

        measures.add(measure);
    }
}
