package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.factory.TechnicalFactory;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.Technicals;
import org.curtis.musicxml.note.notation.technical.Arrow;
import org.curtis.musicxml.note.notation.technical.ArrowDirection;
import org.curtis.musicxml.note.notation.technical.ArrowStyle;
import org.curtis.musicxml.note.notation.technical.CircularArrow;
import org.curtis.musicxml.note.notation.technical.DoubleTongue;
import org.curtis.musicxml.note.notation.technical.DownBow;
import org.curtis.musicxml.note.notation.technical.Fingernails;
import org.curtis.musicxml.note.notation.technical.Handbell;
import org.curtis.musicxml.note.notation.technical.HandbellType;
import org.curtis.musicxml.note.notation.technical.Harmonic;
import org.curtis.musicxml.note.notation.technical.HarmonicPitch;
import org.curtis.musicxml.note.notation.technical.HarmonicType;
import org.curtis.musicxml.note.notation.technical.Hole;
import org.curtis.musicxml.note.notation.technical.HoleClosedLocation;
import org.curtis.musicxml.note.notation.technical.HoleClosedType;
import org.curtis.musicxml.note.notation.technical.OpenString;
import org.curtis.musicxml.note.notation.technical.OtherTechnical;
import org.curtis.musicxml.note.notation.technical.Pluck;
import org.curtis.musicxml.note.notation.technical.SnapPizzicato;
import org.curtis.musicxml.note.notation.technical.Stopped;
import org.curtis.musicxml.note.notation.technical.Tap;
import org.curtis.musicxml.note.notation.technical.Technical;
import org.curtis.musicxml.note.notation.technical.ThumbPosition;
import org.curtis.musicxml.note.notation.technical.TripleTongue;
import org.curtis.musicxml.note.notation.technical.UpBow;
import org.curtis.musicxml.note.notation.technical.Bend;
import org.curtis.musicxml.note.notation.technical.BendType;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class TechnicalHandler extends AbstractHandler {
    private List<Notation> notationList;

    public TechnicalHandler(List<Notation> notationList) {
        this.notationList = notationList;
    }

    public void handle(Element element) {
        List<Element> technicalElements = XmlUtil.getChildElements(element);
        Technicals technicals = new Technicals();
        for (Element technicalElement : technicalElements) {
            String technicalElementName = technicalElement.getTagName();
            Technical technical = null;
            switch (technicalElementName) {
                case "up-bow":
                    UpBow upBow = new UpBow();
                    upBow.setPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = upBow;
                    break;
                case "down-bow":
                    DownBow downBow = new DownBow();
                    downBow.setPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = downBow;
                    break;
                case "harmonic":
                    Harmonic harmonic = new Harmonic();
                    List<Element> harmonicElements = XmlUtil.getChildElements(element);
                    for (Element harmonicElement : harmonicElements) {
                        String harmonicElementName = harmonicElement.getTagName();
                        switch (harmonicElementName) {
                            case "natural":
                                harmonic.setHarmonicType(HarmonicType.NATURAL);
                                break;
                            case "artificial":
                                harmonic.setHarmonicType(HarmonicType.ARTIFICIAL);
                                break;
                            case "base-pitch":
                                harmonic.setHarmonicPitch(HarmonicPitch.BASE_PITCH);
                                break;
                            case "touching-pitch":
                                harmonic.setHarmonicPitch(HarmonicPitch.TOUCHING_PITCH);
                                break;
                            case "sounding-pitch":
                                harmonic.setHarmonicPitch(HarmonicPitch.SOUNDING_PITCH);
                                break;
                        }
                    }
                    harmonic.setPrintObject(FormattingFactory.getPrintObject(element));
                    harmonic.setPrintStyle(FormattingFactory.newPrintStyle(element));
                    harmonic.setPlacement(PlacementFactory.newPlacementLocation(element));
                    technical = harmonic;
                    break;
                case "open-string":
                    OpenString openString = new OpenString();
                    openString.setPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = openString;
                    break;
                case "thumb-position":
                    ThumbPosition thumbPosition = new ThumbPosition();
                    thumbPosition.setPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = thumbPosition;
                    break;
                case "fingering":
                    technical = TechnicalFactory.newFingering(technicalElement);
                    break;
                case "pluck":
                    Pluck pluck = new Pluck();
                    pluck.setValue(PlacementFactory.newPlacementText(technicalElement));
                    technical = pluck;
                    break;
                case "double-tongue":
                    DoubleTongue doubleTongue = new DoubleTongue();
                    doubleTongue.setPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = doubleTongue;
                    break;
                case "triple-tongue":
                    TripleTongue tripleTongue = new TripleTongue();
                    tripleTongue.setPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = tripleTongue;
                    break;
                case "stopped":
                    Stopped stopped = new Stopped();
                    stopped.setPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = stopped;
                    break;
                case "snap-pizzicato":
                    SnapPizzicato snapPizzicato = new SnapPizzicato();
                    snapPizzicato.setPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = snapPizzicato;
                    break;
                case "fret":
                    technical = TechnicalFactory.newFret(technicalElement);
                    break;
                case "string":
                    technical = TechnicalFactory.newStringNumber(technicalElement);
                    break;
                case "hammer-on":
                    technical = TechnicalFactory.newHammerOn(technicalElement);
                    break;
                case "pull-off":
                    technical = TechnicalFactory.newPullOff(technicalElement);
                    break;
                case "bend":
                    Bend bend = new Bend();
                    bend.setBendAlter(MathUtil.newBigDecimal(XmlUtil.getChildElementText(technicalElement, "bend-alter")));
                    List<Element> bendElements = XmlUtil.getChildElements(technicalElement);
                    for (Element bendElement : bendElements) {
                        String bendElementName = bendElement.getTagName();
                        switch (bendElementName) {
                            case "pre-bend":
                                bend.setBendType(BendType.PRE_BEND);
                                break;
                            case "release":
                                bend.setBendType(BendType.RELEASE);
                                break;
                        }
                    }
                    bend.setWithBar(PlacementFactory.newPlacementText(technicalElement));
                    bend.setPrintStyle(FormattingFactory.newPrintStyle(technicalElement));
                    bend.setBendSound(TechnicalFactory.newBendSound(technicalElement));
                    technical = bend;
                    break;
                case "tap":
                    Tap tap = new Tap();
                    tap.setPlacementText(PlacementFactory.newPlacementText(technicalElement));
                    technical = tap;
                    break;
                case "heel":
                    technical = TechnicalFactory.newHeel(technicalElement);
                    break;
                case "toe":
                    technical = TechnicalFactory.newToe(technicalElement);
                    break;
                case "fingernails":
                    Fingernails fingernails = new Fingernails();
                    fingernails.setPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = fingernails;
                    break;
                case "hole":
                    Hole hole = new Hole();
                    hole.setHoleType(XmlUtil.getChildElementText(technicalElement, "hole-type"));
                    Element holeClosedElement = XmlUtil.getChildElement(technicalElement, "hole-closed");
                    String holeClosedType = XmlUtil.getElementText(holeClosedElement);
                    switch (holeClosedType) {
                        case "yes":
                            hole.setHoleClosedType(HoleClosedType.YES);
                            break;
                        case "no":
                            hole.setHoleClosedType(HoleClosedType.NO);
                            break;
                        case "half":
                            hole.setHoleClosedType(HoleClosedType.HALF);
                            break;
                    }
                    String holeClosedLocation = holeClosedElement.getAttribute("location");
                    if (StringUtil.isNotEmpty(holeClosedLocation)) {
                        switch (holeClosedLocation) {
                            case "right":
                                hole.setHoleClosedLocation(HoleClosedLocation.RIGHT);
                                break;
                            case "bottom":
                                hole.setHoleClosedLocation(HoleClosedLocation.BOTTOM);
                                break;
                            case "left":
                                hole.setHoleClosedLocation(HoleClosedLocation.LEFT);
                                break;
                            case "top":
                                hole.setHoleClosedLocation(HoleClosedLocation.TOP);
                                break;
                        }
                    }
                    hole.setPrintStyle(FormattingFactory.newPrintStyle(technicalElement));
                    hole.setPlacement(PlacementFactory.newPlacementLocation(technicalElement));
                    technical = hole;
                    break;
                case "arrow":
                    Arrow arrow = new Arrow();
                    String arrowDirection = XmlUtil.getChildElementText(technicalElement, "arrow-direction");
                    if (StringUtil.isNotEmpty(arrowDirection)) {
                        switch (arrowDirection) {
                            case "left":
                                arrow.setArrowDirection(ArrowDirection.LEFT);
                                break;
                            case "up":
                                arrow.setArrowDirection(ArrowDirection.UP);
                                break;
                            case "right":
                                arrow.setArrowDirection(ArrowDirection.RIGHT);
                                break;
                            case "down":
                                arrow.setArrowDirection(ArrowDirection.DOWN);
                                break;
                            case "northwest":
                                arrow.setArrowDirection(ArrowDirection.NORTHWEST);
                                break;
                            case "northeast":
                                arrow.setArrowDirection(ArrowDirection.NORTHEAST);
                                break;
                            case "southeast":
                                arrow.setArrowDirection(ArrowDirection.SOUTHEAST);
                                break;
                            case "southwest":
                                arrow.setArrowDirection(ArrowDirection.SOUTHWEST);
                                break;
                            case "left right":
                                arrow.setArrowDirection(ArrowDirection.LEFT_RIGHT);
                                break;
                            case "up down":
                                arrow.setArrowDirection(ArrowDirection.UP_DOWN);
                                break;
                            case "northwest southeast":
                                arrow.setArrowDirection(ArrowDirection.NORTHWEST_SOUTHEAST);
                                break;
                            case "northeast southwest":
                                arrow.setArrowDirection(ArrowDirection.NORTHEAST_SOUTHWEST);
                                break;
                            case "other":
                                arrow.setArrowDirection(ArrowDirection.OTHER);
                                break;
                        }
                    }
                    String arrowStyle = XmlUtil.getChildElementText(technicalElement, "arrow-style");
                    if (StringUtil.isNotEmpty(arrowStyle)) {
                        switch (arrowStyle) {
                            case "single":
                                arrow.setArrowStyle(ArrowStyle.SINGLE);
                                break;
                            case "double":
                                arrow.setArrowStyle(ArrowStyle.DOUBLE);
                                break;
                            case "filled":
                                arrow.setArrowStyle(ArrowStyle.FILLED);
                                break;
                            case "hollow":
                                arrow.setArrowStyle(ArrowStyle.HOLLOW);
                                break;
                            case "paired":
                                arrow.setArrowStyle(ArrowStyle.PAIRED);
                                break;
                            case "combined":
                                arrow.setArrowStyle(ArrowStyle.COMBINED);
                                break;
                            case "other":
                                arrow.setArrowStyle(ArrowStyle.OTHER);
                                break;
                        }
                    }
                    String circularArrow = XmlUtil.getChildElementText(technicalElement, "circular-arrow");
                    if (StringUtil.isNotEmpty(circularArrow)) {
                        switch (circularArrow) {
                            case "clockwise":
                                arrow.setCircularArrow(CircularArrow.CLOCKWISE);
                                break;
                            case "anticlockwise":
                                arrow.setCircularArrow(CircularArrow.ANTICLOCKWISE);
                                break;
                        }
                    }
                    arrow.setPrintStyle(FormattingFactory.newPrintStyle(technicalElement));
                    arrow.setPlacement(PlacementFactory.newPlacementLocation(technicalElement));
                    technical = arrow;
                    break;
                case "handbell":
                    Handbell handbell = new Handbell();
                    String handbellType = XmlUtil.getElementText(technicalElement);
                    switch (handbellType) {
                        case "damp":
                            handbell.setHandbellType(HandbellType.DAMP);
                            break;
                        case "echo":
                            handbell.setHandbellType(HandbellType.ECHO);
                            break;
                        case "gyro":
                            handbell.setHandbellType(HandbellType.GYRO);
                            break;
                        case "hand martellato":
                            handbell.setHandbellType(HandbellType.HAND_MARTELLATO);
                            break;
                        case "mallet lift":
                            handbell.setHandbellType(HandbellType.MALLET_LIFT);
                            break;
                        case "mallet table":
                            handbell.setHandbellType(HandbellType.MALLET_TABLE);
                            break;
                        case "martellato":
                            handbell.setHandbellType(HandbellType.MARTELLATO);
                            break;
                        case "martellato lift":
                            handbell.setHandbellType(HandbellType.MARTELLATO_LIFT);
                            break;
                        case "muted martellato":
                            handbell.setHandbellType(HandbellType.MUTED_MARTELLATO);
                            break;
                        case "pluck lift":
                            handbell.setHandbellType(HandbellType.PLUCK_LIFT);
                            break;
                        case "swing":
                            handbell.setHandbellType(HandbellType.SWING);
                            break;
                    }
                    handbell.setPrintStyle(FormattingFactory.newPrintStyle(technicalElement));
                    handbell.setPlacement(PlacementFactory.newPlacementLocation(technicalElement));
                    technical = handbell;
                    break;
                case "other-technical":
                    OtherTechnical otherTechnical = new OtherTechnical();
                    otherTechnical.setPlacementText(PlacementFactory.newPlacementText(technicalElement));
                    technical = otherTechnical;
                    break;
            }
            technicals.setPrintObject(FormattingFactory.getPrintObject(element));
            if (technical != null) technicals.getTechnicals().add(technical);
        }
        notationList.add(technicals);
    }
}
