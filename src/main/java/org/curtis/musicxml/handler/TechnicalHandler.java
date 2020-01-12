package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.factory.TechnicalFactory;
import org.curtis.musicxml.note.notation.Technicals;
import org.curtis.musicxml.note.notation.technical.Arrow;
import org.curtis.musicxml.note.notation.technical.ArrowDirection;
import org.curtis.musicxml.note.notation.technical.ArrowStyle;
import org.curtis.musicxml.note.notation.technical.Bend;
import org.curtis.musicxml.note.notation.technical.BendType;
import org.curtis.musicxml.note.notation.technical.CircularArrow;
import org.curtis.musicxml.note.notation.technical.DoubleTongue;
import org.curtis.musicxml.note.notation.technical.DownBow;
import org.curtis.musicxml.note.notation.technical.Fingernails;
import org.curtis.musicxml.note.notation.technical.Handbell;
import org.curtis.musicxml.note.notation.technical.HandbellType;
import org.curtis.musicxml.note.notation.technical.HarmonClosed;
import org.curtis.musicxml.note.notation.technical.HarmonClosedLocation;
import org.curtis.musicxml.note.notation.technical.HarmonClosedValue;
import org.curtis.musicxml.note.notation.technical.HarmonMute;
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
import org.curtis.musicxml.note.notation.technical.TapHand;
import org.curtis.musicxml.note.notation.technical.Technical;
import org.curtis.musicxml.note.notation.technical.ThumbPosition;
import org.curtis.musicxml.note.notation.technical.TripleTongue;
import org.curtis.musicxml.note.notation.technical.UpBow;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class TechnicalHandler extends BaseHandler {
    private Technicals technicals;

    public TechnicalHandler() {

    }

    public Technicals getTechnicals() {
        return technicals;
    }

    public void setTechnicals(Technicals technicals) {
        this.technicals = technicals;
    }

    public void handle(Element element) {
        List<Element> technicalElements = XmlUtil.getChildElements(element);
        technicals = new Technicals();
        for (Element technicalElement : technicalElements) {
            String technicalElementName = technicalElement.getTagName();
            Technical technical = null;
            switch (technicalElementName) {
                case "up-bow":
                    UpBow upBow = new UpBow();
                    upBow.setPrintPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = upBow;
                    break;
                case "down-bow":
                    DownBow downBow = new DownBow();
                    downBow.setPrintPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = downBow;
                    break;
                case "harmonic":
                    Harmonic harmonic = new Harmonic();
                    List<Element> harmonicElements = XmlUtil.getChildElements(technicalElement);
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
                    harmonic.setPrintObject(FormattingFactory.getPrintObject(technicalElement));
                    harmonic.setPrintStyle(FormattingFactory.newPrintStyle(technicalElement));
                    harmonic.setPlacement(PlacementFactory.newPlacementLocation(technicalElement));
                    technical = harmonic;
                    break;
                case "open-string":
                    OpenString openString = new OpenString();
                    openString.setPrintPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = openString;
                    break;
                case "thumb-position":
                    ThumbPosition thumbPosition = new ThumbPosition();
                    thumbPosition.setPrintPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = thumbPosition;
                    break;
                case "fingering":
                    technical = TechnicalFactory.newFingering(technicalElement);
                    break;
                case "pluck":
                    Pluck pluck = new Pluck();
                    pluck.setPlacementText(PlacementFactory.newPlacementText(technicalElement));
                    technical = pluck;
                    break;
                case "double-tongue":
                    DoubleTongue doubleTongue = new DoubleTongue();
                    doubleTongue.setPrintPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = doubleTongue;
                    break;
                case "triple-tongue":
                    TripleTongue tripleTongue = new TripleTongue();
                    tripleTongue.setPrintPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = tripleTongue;
                    break;
                case "stopped":
                    Stopped stopped = new Stopped();
                    stopped.setPrintPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = stopped;
                    break;
                case "snap-pizzicato":
                    SnapPizzicato snapPizzicato = new SnapPizzicato();
                    snapPizzicato.setPrintPlacement(PlacementFactory.newPlacement(technicalElement));
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
                    for (Element bendElement : bendElements) bend.setBendType((BendType)FactoryUtil.enumValue(BendType.class, bendElement.getTagName()));
                    bend.setWithBar(PlacementFactory.newPlacementText(technicalElement));
                    bend.setPrintStyle(FormattingFactory.newPrintStyle(technicalElement));
                    bend.setBendSound(TechnicalFactory.newBendSound(technicalElement));
                    technical = bend;
                    break;
                case "tap":
                    Tap tap = new Tap();
                    tap.setPlacementText(PlacementFactory.newPlacementText(technicalElement));
                    tap.setTapHand((TapHand)FactoryUtil.enumValue(TapHand.class, technicalElement.getAttribute("hand")));
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
                    fingernails.setPrintPlacement(PlacementFactory.newPlacement(technicalElement));
                    technical = fingernails;
                    break;
                case "hole":
                    Hole hole = new Hole();
                    hole.setHoleType(XmlUtil.getChildElementText(technicalElement, "hole-type"));
                    Element holeClosedElement = XmlUtil.getChildElement(technicalElement, "hole-closed");
                    hole.setHoleClosedType((HoleClosedType)FactoryUtil.enumValue(HoleClosedType.class, XmlUtil.getElementText(holeClosedElement)));
                    hole.setHoleClosedLocation((HoleClosedLocation)FactoryUtil.enumValue(HoleClosedLocation.class, holeClosedElement.getAttribute("location")));
                    hole.setPrintStyle(FormattingFactory.newPrintStyle(technicalElement));
                    hole.setPlacement(PlacementFactory.newPlacementLocation(technicalElement));
                    technical = hole;
                    break;
                case "arrow":
                    Arrow arrow = new Arrow();
                    arrow.setArrowDirection((ArrowDirection)FactoryUtil.enumValue(ArrowDirection.class, XmlUtil.getChildElementText(technicalElement, "arrow-direction")));
                    arrow.setArrowStyle((ArrowStyle)FactoryUtil.enumValue(ArrowStyle.class, XmlUtil.getChildElementText(technicalElement, "arrow-style")));
                    arrow.setCircularArrow((CircularArrow)FactoryUtil.enumValue(CircularArrow.class, XmlUtil.getChildElementText(technicalElement, "circular-arrow")));
                    arrow.setPrintStyle(FormattingFactory.newPrintStyle(technicalElement));
                    arrow.setPlacement(PlacementFactory.newPlacementLocation(technicalElement));
                    technical = arrow;
                    break;
                case "handbell":
                    Handbell handbell = new Handbell();
                    String handbellType = XmlUtil.getElementText(technicalElement);
                    handbell.setHandbellType((HandbellType) FactoryUtil.enumValue(HandbellType.class, handbellType));
                    handbell.setPrintStyle(FormattingFactory.newPrintStyle(technicalElement));
                    handbell.setPlacement(PlacementFactory.newPlacementLocation(technicalElement));
                    technical = handbell;
                    break;
                case "harmon-mute":
                    HarmonMute harmonMute = new HarmonMute();
                    Element harmonClosedElement = XmlUtil.getChildElement(technicalElement, "harmon-closed");
                    HarmonClosed harmonClosed = new HarmonClosed();
                    harmonClosed.setValue((HarmonClosedValue) FactoryUtil.enumValue(HarmonClosedValue.class, XmlUtil.getElementText(harmonClosedElement)));
                    harmonClosed.setLocation((HarmonClosedLocation)FactoryUtil.enumValue(HarmonClosedLocation.class, harmonClosedElement.getAttribute("location")));
                    harmonMute.setHarmonClosed(harmonClosed);
                    technical = harmonMute;
                    break;
                case "other-technical":
                    OtherTechnical otherTechnical = new OtherTechnical();
                    otherTechnical.setPlacementText(PlacementFactory.newPlacementText(technicalElement));
                    technical = otherTechnical;
                    break;
            }
            if (technical != null) {
                technicals.getTechnicals().add(technical);
                technical.setTechnicals(technicals);
            }
        }
    }
}
