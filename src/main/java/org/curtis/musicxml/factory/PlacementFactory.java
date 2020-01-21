package org.curtis.musicxml.factory;

import org.curtis.musicxml.note.PlacementText;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

public class PlacementFactory {
    private PlacementFactory() {

    }

    public static PlacementText newPlacementText(Element element) {
        if (element == null) return null;

        PlacementText placementText = new PlacementText();
        placementText.setValue(XmlUtil.getElementText(element));
        placementText.setDisplay(DisplayFactory.newDisplay(element));

        return placementText;
    }
}
