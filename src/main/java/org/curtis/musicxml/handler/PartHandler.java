package org.curtis.musicxml.handler;

import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.Part;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

import static org.curtis.musicxml.util.MusicXmlUtil.DEBUG;

public class PartHandler implements ScoreElementHandler {
    private List<Part> parts;

    public PartHandler(List<Part> parts) {
        this.parts = parts;
    }

    public void handle(Element element) {
        Part part = new Part();

        part.setPartId(element.getAttribute("id"));
        if (DEBUG) System.err.println("Part " + part.getPartId());

        List<Measure> measures = part.getMeasures();
        List<Element> measureElements = XmlUtil.getChildElements(element, "measure");
        for(Element measureElement : measureElements) {
            MeasureHandler measureHandler = new MeasureHandler(measures);
            measureHandler.handle(measureElement);
        }

        parts.add(part);
    }
}
