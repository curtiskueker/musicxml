package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.PlacementText;
import org.curtis.util.MathUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

public class PlacementFactory {
    private PlacementFactory() {

    }

    public static Placement newPlacement(Element element) {
        if (element == null) return null;

        Placement placement = new Placement();

        PrintStyle printStyle = FormattingFactory.newPrintStyle(element);
        placement.setPrintStyle(printStyle);

        placement.setPlacement(PlacementFactory.newPlacementLocation(element));

        return placement;
    }

    public static Location newPlacementLocation(Element element) {
        if (element == null) return null;

        return PlacementUtil.getLocation(element.getAttribute("placement"));
    }

    public static PlacementText newPlacementText(Element element) {
        if (element == null) return null;

        PlacementText placementText = new PlacementText();
        placementText.setValue(XmlUtil.getElementText(element));
        placementText.setPrintStyle(FormattingFactory.newPrintStyle(element));
        placementText.setPlacement(PlacementFactory.newPlacementLocation(element));

        return placementText;
    }

    public static Position newPosition(Element positionElement) {
        if(positionElement == null) {
            return null;
        }

        Position position = new Position();

        position.setDefaultX(MathUtil.newBigDecimal(positionElement.getAttribute("default-x")));
        position.setDefaultY(MathUtil.newBigDecimal(positionElement.getAttribute("default-y")));
        position.setRelativeX(MathUtil.newBigDecimal(positionElement.getAttribute("relative-x")));
        position.setRelativeY(MathUtil.newBigDecimal(positionElement.getAttribute("relative-y")));

        return position;
    }
}
