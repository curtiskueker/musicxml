package org.curtis.musicxml.handler;

import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.Part;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class PartHandler extends AbstractHandler {
    private List<Part> parts;

    public PartHandler(List<Part> parts) {
        this.parts = parts;
    }

    public void handle(Element element) {
        Part part = new Part();

        part.setId(element.getAttribute("id"));

        List<Measure> measures = part.getMeasures();
        List<Element> measureElements = XmlUtil.getChildElements(element, "measure");
        for(Element measureElenent : measureElements) {
            MeasureHandler measureHandler = new MeasureHandler(measures);
            measureHandler.handle(measureElenent);
        }

        parts.add(part);
    }
}
