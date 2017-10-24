package org.curtis.musicxml.handler;

import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.Part;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class PartHandler extends AbstractHandler {
    private List<Part> parts;

    public PartHandler(Element element, List<Part> parts) {
        super(element);
        this.parts = parts;
    }

    public StringBuilder handle() {
        Part part = new Part();

        part.setId(getElement().getAttribute("id"));

        List<Measure> measures = part.getMeasures();
        List<Element> measureElements = XmlUtil.getChildElements(getElement(), "measure");
        for(Element measureElenent : measureElements) {
            MeasureHandler measureHandler = new MeasureHandler(measureElenent, measures);
            measureHandler.handle();
        }

        parts.add(part);
        return stringBuilder;
    }
}
