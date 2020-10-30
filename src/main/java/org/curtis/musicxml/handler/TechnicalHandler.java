package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.DisplayFactory;
import org.curtis.musicxml.factory.FactoryUtil;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.TechnicalFactory;
import org.curtis.musicxml.note.notation.Notation;
import org.curtis.musicxml.note.notation.Technicals;
import org.curtis.musicxml.note.notation.technical.Arrow;
import org.curtis.musicxml.note.notation.technical.ArrowDirection;
import org.curtis.musicxml.note.notation.technical.ArrowStyle;
import org.curtis.musicxml.note.notation.technical.Bend;
import org.curtis.musicxml.note.notation.technical.BendType;
import org.curtis.musicxml.note.notation.technical.BrassBend;
import org.curtis.musicxml.note.notation.technical.CircularArrow;
import org.curtis.musicxml.note.notation.technical.DoubleTongue;
import org.curtis.musicxml.note.notation.technical.DownBow;
import org.curtis.musicxml.note.notation.technical.Fingernails;
import org.curtis.musicxml.note.notation.technical.Flip;
import org.curtis.musicxml.note.notation.technical.Golpe;
import org.curtis.musicxml.note.notation.technical.HalfMuted;
import org.curtis.musicxml.note.notation.technical.Handbell;
import org.curtis.musicxml.note.notation.technical.HandbellType;
import org.curtis.musicxml.note.notation.technical.HarmonClosedLocation;
import org.curtis.musicxml.note.notation.technical.HarmonClosedValue;
import org.curtis.musicxml.note.notation.technical.HarmonMute;
import org.curtis.musicxml.note.notation.technical.Harmonic;
import org.curtis.musicxml.note.notation.technical.HarmonicPitch;
import org.curtis.musicxml.note.notation.technical.HarmonicType;
import org.curtis.musicxml.note.notation.technical.Hole;
import org.curtis.musicxml.note.notation.technical.HoleClosedLocation;
import org.curtis.musicxml.note.notation.technical.HoleClosedType;
import org.curtis.musicxml.note.notation.technical.Open;
import org.curtis.musicxml.note.notation.technical.OpenString;
import org.curtis.musicxml.note.notation.technical.OtherTechnical;
import org.curtis.musicxml.note.notation.technical.Pluck;
import org.curtis.musicxml.note.notation.technical.Smear;
import org.curtis.musicxml.note.notation.technical.SnapPizzicato;
import org.curtis.musicxml.note.notation.technical.Stopped;
import org.curtis.musicxml.note.notation.technical.Tap;
import org.curtis.musicxml.note.notation.technical.TapHand;
import org.curtis.musicxml.note.notation.technical.Technical;
import org.curtis.musicxml.note.notation.technical.ThumbPosition;
import org.curtis.musicxml.note.notation.technical.TripleTongue;
import org.curtis.musicxml.note.notation.technical.UpBow;
import org.curtis.util.MathUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class TechnicalHandler implements NotationHandler {
    public Notation handle(Element element) {
        List<Element> technicalElements = XmlUtil.getChildElements(element);
        Technicals technicals = new Technicals();
        for (Element technicalElement : technicalElements) {
            String technicalElementName = technicalElement.getTagName();
            Technical technical = null;
            switch (technicalElementName) {
                case "up-bow":
                    technical = new UpBow();
                    break;
                case "down-bow":
                    technical = new DownBow();
                    break;
                case "harmonic":
                    Harmonic harmonic = new Harmonic();
                    List<Element> harmonicElements = XmlUtil.getChildElements(technicalElement);
                    for (Element harmonicElement : harmonicElements) {
                        String harmonicElementName = harmonicElement.getTagName();
                        switch (harmonicElementName) {
                            case "natural":
                                harmonic.setType(HarmonicType.NATURAL);
                                break;
                            case "artificial":
                                harmonic.setType(HarmonicType.ARTIFICIAL);
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
                    technical = harmonic;
                    break;
                case "open-string":
                    technical = new OpenString();
                    break;
                case "thumb-position":
                    technical = new ThumbPosition();
                    break;
                case "fingering":
                    technical = TechnicalFactory.newFingering(technicalElement);
                    break;
                case "pluck":
                    Pluck pluck = new Pluck();
                    pluck.setValue(XmlUtil.getElementText(technicalElement));
                    technical = pluck;
                    break;
                case "double-tongue":
                    technical = new DoubleTongue();
                    break;
                case "triple-tongue":
                    technical = new TripleTongue();
                    break;
                case "stopped":
                    Stopped stopped = new Stopped();
                    stopped.setSmufl(technicalElement.getAttribute("smufl"));
                    technical = stopped;
                    break;
                case "snap-pizzicato":
                    technical = new SnapPizzicato();
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
                    for (Element bendElement : bendElements) bend.setType(FactoryUtil.enumValue(BendType.class, bendElement.getTagName()));
                    bend.setWithBar(TechnicalFactory.newBendWithBar(XmlUtil.getChildElement(technicalElement, "with-bar")));
                    bend.setBendSound(TechnicalFactory.newBendSound(technicalElement));
                    technical = bend;
                    break;
                case "tap":
                    Tap tap = new Tap();
                    tap.setValue(XmlUtil.getElementText(technicalElement));
                    tap.setTapHand(FactoryUtil.enumValue(TapHand.class, technicalElement.getAttribute("hand")));
                    technical = tap;
                    break;
                case "heel":
                    technical = TechnicalFactory.newHeel(technicalElement);
                    break;
                case "toe":
                    technical = TechnicalFactory.newToe(technicalElement);
                    break;
                case "fingernails":
                    technical = new Fingernails();
                    break;
                case "brass-bend":
                    technical = new BrassBend();
                    break;
                case "flip":
                    technical = new Flip();
                    break;
                case "smear":
                    technical = new Smear();
                    break;
                case "open":
                    Open open = new Open();
                    open.setSmufl(technicalElement.getAttribute("smufl"));
                    technical = open;
                    break;
                case "half-muted":
                    HalfMuted halfMuted = new HalfMuted();
                    halfMuted.setSmufl(technicalElement.getAttribute("smufl"));
                    technical = halfMuted;
                    break;
                case "golpe":
                    technical = new Golpe();
                    break;
                case "hole":
                    Hole hole = new Hole();
                    hole.setType(XmlUtil.getChildElementText(technicalElement, "hole-type"));
                    Element holeClosedElement = XmlUtil.getChildElement(technicalElement, "hole-closed");
                    hole.setHoleClosed(FactoryUtil.enumValue(HoleClosedType.class, XmlUtil.getElementText(holeClosedElement)));
                    hole.setHoleClosedLocation(FactoryUtil.enumValue(HoleClosedLocation.class, holeClosedElement.getAttribute("location")));
                    technical = hole;
                    break;
                case "arrow":
                    Arrow arrow = new Arrow();
                    arrow.setArrowDirection(FactoryUtil.enumValue(ArrowDirection.class, XmlUtil.getChildElementText(technicalElement, "arrow-direction")));
                    arrow.setArrowStyle(FactoryUtil.enumValue(ArrowStyle.class, XmlUtil.getChildElementText(technicalElement, "arrow-style")));
                    arrow.setArrowhead(XmlUtil.hasChildElement(technicalElement, "arrowhead"));
                    arrow.setCircularArrow(FactoryUtil.enumValue(CircularArrow.class, XmlUtil.getChildElementText(technicalElement, "circular-arrow")));
                    technical = arrow;
                    break;
                case "handbell":
                    Handbell handbell = new Handbell();
                    String handbellType = XmlUtil.getElementText(technicalElement);
                    handbell.setValue(FactoryUtil.enumValue(HandbellType.class, handbellType));
                    technical = handbell;
                    break;
                case "harmon-mute":
                    HarmonMute harmonMute = new HarmonMute();
                    Element harmonClosedElement = XmlUtil.getChildElement(technicalElement, "harmon-closed");
                    harmonMute.setValue(FactoryUtil.enumValue(HarmonClosedValue.class, XmlUtil.getElementText(harmonClosedElement)));
                    harmonMute.setLocation(FactoryUtil.enumValue(HarmonClosedLocation.class, harmonClosedElement.getAttribute("location")));
                    technical = harmonMute;
                    break;
                case "other-technical":
                    OtherTechnical otherTechnical = new OtherTechnical();
                    otherTechnical.setValue(XmlUtil.getElementText(technicalElement));
                    otherTechnical.setSmufl(technicalElement.getAttribute("smufl"));
                    technical = otherTechnical;
                    break;
            }
            if (technical != null) {
                technical.setDisplay(DisplayFactory.newDisplay(technicalElement));
                technicals.getTechnicals().add(technical);
            }
        }

        return technicals;
    }
}
