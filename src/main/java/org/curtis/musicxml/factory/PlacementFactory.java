package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.PlacementText;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

public class PlacementFactory {
    private PlacementFactory() {

    }

    public static Placement newPlacement(Element placementElement) {
        Placement placement = new Placement();

        PrintStyle printStyle = FormattingFactory.newPrintStyle(placementElement);
        placement.setPrintStyle(printStyle);

        placement.setPlacement(PlacementUtil.getLocation(placementElement.getAttribute("placement")));

        return placement;
    }

    public static PlacementText newPlacementText(Element placementTextElement) {
        PlacementText placementText = new PlacementText();
        placementText.setValue(XmlUtil.getElementText(placementTextElement));
        placementText.setPrintStyle(FormattingFactory.newPrintStyle(placementTextElement));
        placementText.setPlacement(PlacementUtil.getLocation(placementTextElement.getAttribute("placement")));

        return placementText;
    }
}
