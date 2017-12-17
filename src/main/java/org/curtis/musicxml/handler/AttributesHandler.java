package org.curtis.musicxml.handler;

import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.attributes.Clef;
import org.curtis.musicxml.attributes.ClefSign;
import org.curtis.musicxml.attributes.Directive;
import org.curtis.musicxml.attributes.ShowFrets;
import org.curtis.musicxml.attributes.StaffDetails;
import org.curtis.musicxml.attributes.StaffTuning;
import org.curtis.musicxml.attributes.StaffType;
import org.curtis.musicxml.attributes.time.Time;
import org.curtis.musicxml.attributes.time.TimeSignature;
import org.curtis.musicxml.attributes.Transpose;
import org.curtis.musicxml.attributes.key.Key;
import org.curtis.musicxml.attributes.measure.BeatRepeat;
import org.curtis.musicxml.attributes.measure.MeasureRepeat;
import org.curtis.musicxml.attributes.measure.MeasureStyle;
import org.curtis.musicxml.attributes.measure.MultipleRest;
import org.curtis.musicxml.attributes.measure.Slash;
import org.curtis.musicxml.factory.AttributesFactory;
import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.factory.PlacementFactory;
import org.curtis.musicxml.handler.util.PlacementUtil;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.PartSymbol;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.math.BigDecimal;
import java.util.List;

public class AttributesHandler extends MusicDataHandler {
    public AttributesHandler() {
    }

    public MusicData handle(Element element) {
        Attributes attributes = new Attributes();

        List<Element> attributesSubelements = XmlUtil.getChildElements(element);
        for(Element attributeSubelement : attributesSubelements) {
            switch (attributeSubelement.getTagName()) {
                case "divisions":
                    attributes.setDivisions(new BigDecimal(XmlUtil.getElementText(attributeSubelement)));
                    break;
                case "key":
                    List<Key> keys = attributes.getKeys();
                    Key key = AttributesFactory.newKey(attributeSubelement);
                    key.setNumber(StringUtil.getInteger(attributeSubelement.getAttribute("number")));
                    key.setPrintStyle(FormattingFactory.newPrintStyle(attributeSubelement));
                    key.setPrintObject(TypeUtil.getYesNo(attributeSubelement.getAttribute("print-object")));
                    keys.add(key);
                    break;
                case "time":
                    List<Time> timeList = attributes.getTimeList();
                    Time time = AttributesFactory.newTime(attributeSubelement);
                    time.setNumber(StringUtil.getInteger(attributeSubelement.getAttribute("number")));
                    time.setSymbol(AttributesFactory.newTimeSymbol(attributeSubelement));
                    time.setSeparator(AttributesFactory.newTimeSeparator(attributeSubelement));
                    time.setPrintStyleAlign(FormattingFactory.newPrintStyleAlign(attributeSubelement));
                    time.setPrintObject(TypeUtil.getYesNo(attributeSubelement.getAttribute("print-object")));
                    timeList.add(time);
                    break;
                case "staves":
                    attributes.setStaves(StringUtil.getInteger(XmlUtil.getElementText(attributeSubelement)));
                    break;
                case "part-symbol":
                    PartSymbol partSymbol = new PartSymbol();
                    partSymbol.setGroupSymbolType(AttributesFactory.newGroupSymbolType(attributeSubelement));
                    partSymbol.setTopStaff(StringUtil.getInteger(attributeSubelement.getAttribute("top-staff")));
                    partSymbol.setBottomStaff(StringUtil.getInteger(attributeSubelement.getAttribute("bottom-staff")));
                    partSymbol.setPosition(PlacementFactory.newPosition(attributeSubelement));
                    partSymbol.setColor(attributeSubelement.getAttribute("color"));
                    attributes.setPartSymbol(partSymbol);
                    break;
                case "instruments":
                    attributes.setInstruments(StringUtil.getInteger(XmlUtil.getElementText(attributeSubelement)));
                    break;
                case "clef":
                    Clef clef = new Clef();
                    String sign = XmlUtil.getChildElementText(attributeSubelement, "sign");
                    ClefSign clefSign;
                    switch (sign) {
                        case "G":
                            clefSign = ClefSign.G;
                            break;
                        case "F":
                            clefSign = ClefSign.F;
                            break;
                        case "C":
                            clefSign = ClefSign.C;
                            break;
                        case "percussion":
                            clefSign = ClefSign.PERCUSSION;
                            break;
                        case "TAB":
                            clefSign = ClefSign.TAB;
                            break;
                        case "jianpu":
                            clefSign = ClefSign.JIANPU;
                            break;
                        case "none":
                            clefSign = ClefSign.NONE;
                            break;
                        default:
                            clefSign = null;
                    }
                    clef.setSign(clefSign);
                    clef.setLine(StringUtil.getInteger(XmlUtil.getChildElementText(attributeSubelement, "line")));
                    clef.setClefOctaveChange(StringUtil.getInteger(XmlUtil.getChildElementText(attributeSubelement, "clef-octave-change")));
                    clef.setNumber(StringUtil.getInteger(attributeSubelement.getAttribute("number")));
                    clef.setAdditional(TypeUtil.getYesNo(attributeSubelement.getAttribute("additional")));
                    clef.setSize(FormattingFactory.newSymbolSize(attributeSubelement));
                    clef.setAfterBarline(TypeUtil.getYesNo(attributeSubelement.getAttribute("after-barline")));
                    clef.setPrintStyle(FormattingFactory.newPrintStyle(attributeSubelement));
                    clef.setPrintObject(TypeUtil.getYesNo(attributeSubelement.getAttribute("print-object")));
                    attributes.setClef(clef);
                    break;
                case "staff-details":
                    List<StaffDetails> staffDetailsList = attributes.getStaffDetailsList();
                    StaffDetails staffDetails = new StaffDetails();
                    List<Element> staffDetailsSubelements = XmlUtil.getChildElements(attributeSubelement);
                    for(Element staffDetailsSubelement : staffDetailsSubelements) {
                        String staffDefaultsSubelementName = staffDetailsSubelement.getTagName();
                        switch (staffDefaultsSubelementName) {
                            case "staff-type":
                                String staffType = XmlUtil.getElementText(staffDetailsSubelement);
                                switch (staffType) {
                                    case "ossia":
                                        staffDetails.setStaffType(StaffType.OSSIA);
                                        break;
                                    case "cue":
                                        staffDetails.setStaffType(StaffType.CUE);
                                        break;
                                    case "editorial":
                                        staffDetails.setStaffType(StaffType.EDITORIAL);
                                        break;
                                    case "regular":
                                        staffDetails.setStaffType(StaffType.REGULAR);
                                        break;
                                    case "alternate":
                                        staffDetails.setStaffType(StaffType.ALTERNATE);
                                        break;
                                }
                                break;
                            case "staff-lines":
                                staffDetails.setStaffLines(StringUtil.getInteger(XmlUtil.getChildElementText(staffDetailsSubelement, "staff-lines")));
                                break;
                            case "staff-tuning":
                                List<StaffTuning> staffTunings = staffDetails.getStaffTunings();
                                StaffTuning staffTuning = new StaffTuning();
                                staffTuning.setTuning(AttributesFactory.newTuning(staffDetailsSubelement));
                                staffTunings.add(staffTuning);
                                break;
                            case "capo":
                                staffDetails.setCapo(StringUtil.getInteger(XmlUtil.getElementText(staffDetailsSubelement)));
                                break;
                            case "staff-size":
                                staffDetails.setStaffSize(MathUtil.newBigDecimal(XmlUtil.getElementText(staffDetailsSubelement)));
                                break;
                        }
                    }
                    staffDetails.setNumber(StringUtil.getInteger(attributeSubelement.getAttribute("number")));
                    String showFrets = attributeSubelement.getAttribute("show-frets");
                    if(StringUtil.isNotEmpty(showFrets)) {
                        switch (showFrets) {
                            case "numbers":
                                staffDetails.setShowFrets(ShowFrets.NUMBERS);
                                break;
                            case "letters":
                                staffDetails.setShowFrets(ShowFrets.LETTERS);
                                break;
                        }
                    }
                    staffDetails.setPrintObject(TypeUtil.getYesNo(attributeSubelement.getAttribute("print-object")));
                    staffDetails.setPrintSpacing(TypeUtil.getYesNo(attributeSubelement.getAttribute("print-spacing")));
                    staffDetailsList.add(staffDetails);
                    break;
                case "transpose":
                    List<Transpose> transpositions = attributes.getTranspositions();
                    Transpose transpose = new Transpose();
                    List<Element> transposeSubelements = XmlUtil.getChildElements(attributeSubelement);
                    for(Element transposeSubelement : transposeSubelements) {
                        String transposeSubelementName = transposeSubelement.getTagName();
                        switch (transposeSubelementName) {
                            case "diatonic":
                                transpose.setDiatonic(StringUtil.getInteger(XmlUtil.getElementText(transposeSubelement)));
                                break;
                            case "chromatic":
                                transpose.setChromatic(MathUtil.newBigDecimal(XmlUtil.getElementText(transposeSubelement)));
                                break;
                            case "octave-change":
                                transpose.setOctaveChange(StringUtil.getInteger(XmlUtil.getElementText(transposeSubelement)));
                                break;
                            case "double":
                                transpose.setDoubled(true);
                                break;
                        }
                    }
                    transpose.setNumber(StringUtil.getInteger(attributeSubelement.getAttribute("number")));
                    transpositions.add(transpose);
                    break;
                case "directive":
                    Directive directive = new Directive();
                    directive.setValue(XmlUtil.getElementText(attributeSubelement));
                    directive.setPrintStyle(FormattingFactory.newPrintStyle(attributeSubelement));
                    directive.setLang(attributeSubelement.getAttribute("lang"));
                    break;
                case "measure-style":
                    MeasureStyle measureStyle = null;
                    List<Element> measureStyleSubelements = XmlUtil.getChildElements(attributeSubelement);
                    for(Element measureStyleSubelement : measureStyleSubelements) {
                        String measureStyleSubelementName = measureStyleSubelement.getTagName();
                        switch (measureStyleSubelementName) {
                            case "multiple-rest":
                                MultipleRest multipleRest = new MultipleRest();
                                multipleRest.setValue(StringUtil.getInteger(XmlUtil.getElementText(measureStyleSubelement)));
                                multipleRest.setUseSymbols(TypeUtil.getYesNo(measureStyleSubelement.getAttribute("use-symbols")));
                                measureStyle = multipleRest;
                                break;
                            case "measure-repeat":
                                MeasureRepeat measureRepeat = new MeasureRepeat();
                                measureRepeat.setValue(StringUtil.getInteger(XmlUtil.getElementText(measureStyleSubelement)));
                                measureRepeat.setType(PlacementUtil.getConnection(measureStyleSubelement.getAttribute("type")));
                                measureRepeat.setSlashes(StringUtil.getInteger(measureStyleSubelement.getAttribute("slashes")));
                                measureStyle = measureRepeat;
                                break;
                            case "beat-repeat":
                                BeatRepeat beatRepeat = new BeatRepeat();
                                beatRepeat.setSlashGroup(AttributesFactory.newSlashGroup(measureStyleSubelement));
                                beatRepeat.setType(PlacementUtil.getConnection(measureStyleSubelement.getAttribute("type")));
                                beatRepeat.setSlashes(StringUtil.getInteger(measureStyleSubelement.getAttribute("slashes")));
                                beatRepeat.setUseDots(TypeUtil.getYesNo(measureStyleSubelement.getAttribute("use-dots")));
                                measureStyle = beatRepeat;
                                break;
                            case "slash":
                                Slash slash = new Slash();
                                slash.setSlashGroup(AttributesFactory.newSlashGroup(measureStyleSubelement));
                                slash.setType(PlacementUtil.getConnection(measureStyleSubelement.getAttribute("type")));
                                slash.setUseDots(TypeUtil.getYesNo(measureStyleSubelement.getAttribute("use-dots")));
                                slash.setUseStems(TypeUtil.getYesNo(measureStyleSubelement.getAttribute("use-stems")));
                                measureStyle = slash;
                                break;
                        }
                    }
                    if(measureStyle != null) {
                        measureStyle.setNumber(StringUtil.getInteger(attributeSubelement.getAttribute("number")));
                        measureStyle.setFont(FormattingFactory.newFont(attributeSubelement));
                        measureStyle.setColor(attributeSubelement.getAttribute("color"));
                    }
                    break;
            }
        }

        return attributes;
    }
}
