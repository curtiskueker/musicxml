package org.curtis.musicxml.factory;

import org.curtis.musicxml.common.Location;
import org.curtis.musicxml.common.Position;
import org.curtis.musicxml.common.PrintStyle;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.note.Placement;
import org.curtis.musicxml.note.PlacementText;
import org.curtis.util.MathUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.math.BigDecimal;

public class PlacementFactory {
    private PlacementFactory() {

    }

    public static Placement newPlacement(Element element) {
        if (element == null) return null;

        PrintStyle printStyle = FormattingFactory.newPrintStyle(element);
        Location placementLocation = PlacementFactory.newPlacementLocation(element);

        if (printStyle == null && placementLocation == null) return null;

        Placement placement = new Placement();
        placement.setPrintStyle(printStyle);
        placement.setPlacement(placementLocation);

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

    public static Position newPosition(Element element) {
        if(element == null) {
            return null;
        }

        BigDecimal defaultX = MathUtil.newBigDecimal(element.getAttribute("default-x"));
        BigDecimal defaultY = MathUtil.newBigDecimal(element.getAttribute("default-y"));
        BigDecimal relativeX = MathUtil.newBigDecimal(element.getAttribute("relative-x"));
        BigDecimal relativeY = MathUtil.newBigDecimal(element.getAttribute("relative-y"));

        if (!validPositionValue(defaultX, "default-x")) defaultX = null;
        if (!validPositionValue(defaultY, "default-y")) defaultY = null;
        if (!validPositionValue(relativeX, "relative-x")) relativeX = null;
        if (!validPositionValue(relativeY, "relative-y")) relativeY = null;

        if (defaultX == null && defaultY == null && relativeX == null && relativeY == null) return null;

        Position position = new Position();

        position.setDefaultX(defaultX);
        position.setDefaultY(defaultY);
        position.setRelativeX(relativeX);
        position.setRelativeY(relativeY);

        return position;
    }

    private static boolean validPositionValue(BigDecimal positionValue, String fieldName) {
        if (positionValue == null) return true;

        if (MathUtil.largerThan(positionValue.abs(), TypeUtil.MAXIMUM_POSITION_VALUE)) {
            System.err.println("Warning: " + fieldName + " value " + positionValue + " exceeds maximum allowed value " + TypeUtil.MAXIMUM_POSITION_VALUE + ".  Setting value to null.");
            return false;
        }

        return true;
    }
}
