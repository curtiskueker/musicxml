package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.note.Placement;
import org.w3c.dom.Element;

public class PlacementFactory {
    private PlacementFactory() {

    }

    public static Placement newPlacement(Element placementElement) {
        Placement placement = new Placement();

        PrintStyle printStyle = FormatFactory.newPrintStyle(placementElement);
        placement.setPrintStyle(printStyle);

        placement.setPlacement(PlacementUtil.getLocation(placementElement.getAttribute("placement")));

        return placement;
    }
}
