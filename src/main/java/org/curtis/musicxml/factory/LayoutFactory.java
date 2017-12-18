package org.curtis.musicxml.factory;

import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.MarginType;
import org.curtis.musicxml.layout.Margins;
import org.curtis.musicxml.layout.PageLayout;
import org.curtis.musicxml.layout.PageMargins;
import org.curtis.musicxml.layout.StaffLayout;
import org.curtis.musicxml.layout.SystemDividers;
import org.curtis.musicxml.layout.SystemLayout;
import org.curtis.musicxml.layout.SystemMargins;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;
import java.util.Map;

public class LayoutFactory {
    private LayoutFactory() {

    }

    public static Layout newLayout(Element layoutElement) {
        Layout layout = new Layout();

        List<Element> layoutSubelements = XmlUtil.getChildElements(layoutElement);
        for(Element layoutSubelement : layoutSubelements) {
            String layoutSubelementName = layoutSubelement.getTagName();
            switch (layoutSubelementName) {
                case "page-layout":
                    PageLayout pageLayout = new PageLayout();
                    pageLayout.setPageHeight(MathUtil.newBigDecimal(XmlUtil.getChildElementText(layoutSubelement, "page-height")));
                    pageLayout.setPageWidth(MathUtil.newBigDecimal(XmlUtil.getChildElementText(layoutSubelement, "page-width")));

                    List<Element> pageMarginsElements = XmlUtil.getChildElements(layoutSubelement, "page-margins");
                    Map<MarginType, PageMargins> pageMarginsMap = pageLayout.getPageMargins();
                    for(Element pageMarginsElement : pageMarginsElements) {
                        PageMargins pageMargins = new PageMargins();

                        Margins margins = new Margins();
                        margins.setLeftMargin(MathUtil.newBigDecimal(XmlUtil.getChildElementText(pageMarginsElement, "left-margin")));
                        margins.setRightMargin(MathUtil.newBigDecimal(XmlUtil.getChildElementText(pageMarginsElement, "right-margin")));
                        margins.setTopMargin(MathUtil.newBigDecimal(XmlUtil.getChildElementText(pageMarginsElement, "top-margin")));
                        margins.setBottomMargin(MathUtil.newBigDecimal(XmlUtil.getChildElementText(pageMarginsElement, "bottom-margin")));
                        pageMargins.setMargins(margins);

                        String marginType = pageMarginsElement.getAttribute("type");
                        if(StringUtil.isNotEmpty(marginType)) {
                            switch (marginType) {
                                case "odd":
                                    pageMargins.setType(MarginType.ODD);
                                    break;
                                case "even":
                                    pageMargins.setType(MarginType.EVEN);
                                    break;
                                case "both":
                                    pageMargins.setType(MarginType.BOTH);
                                    break;
                            }
                        }

                        pageMarginsMap.put(pageMargins.getType(), pageMargins);
                    }
                    layout.setPageLayout(pageLayout);
                    break;
                case "system-layout":
                    SystemLayout systemLayout = new SystemLayout();
                    Element systemMarginsElement = XmlUtil.getChildElement(layoutSubelement, "system-margins");
                    if(systemMarginsElement != null) {
                        SystemMargins systemMargins = new SystemMargins();
                        systemMargins.setLeftMargin(MathUtil.newBigDecimal(XmlUtil.getChildElementText(systemMarginsElement, "left-margin")));
                        systemMargins.setRightMargin(MathUtil.newBigDecimal(XmlUtil.getChildElementText(systemMarginsElement, "right-margin")));
                        systemLayout.setSystemMargins(systemMargins);
                    }

                    systemLayout.setSystemDistance(MathUtil.newBigDecimal(XmlUtil.getChildElementText(layoutSubelement, "system-distance")));
                    systemLayout.setTopSystemDistance(MathUtil.newBigDecimal(XmlUtil.getChildElementText(layoutSubelement, "top-system-distance")));

                    Element systemDividersElement = XmlUtil.getChildElement(layoutSubelement, "system-dividers");
                    if(systemDividersElement != null) {
                        SystemDividers systemDividers = new SystemDividers();
                        systemDividers.setLeftDivider(FormattingFactory.newPrintObjectStyleAlign(XmlUtil.getChildElement(layoutSubelement, "left-divider")));
                        systemDividers.setRightDivider(FormattingFactory.newPrintObjectStyleAlign(XmlUtil.getChildElement(layoutSubelement, "right-divider")));
                        systemLayout.setSystemDividers(systemDividers);
                    }
                    layout.setSystemLayout(systemLayout);
                    break;
                case "staff-layout":
                    List<StaffLayout> staffLayouts = layout.getStaffLayouts();

                    StaffLayout staffLayout = new StaffLayout();
                    staffLayout.setStaffDistance(MathUtil.newBigDecimal(XmlUtil.getChildElementText(layoutSubelement, "staff-distance")));
                    staffLayout.setNumber(StringUtil.getInteger(layoutSubelement.getAttribute("number")));

                    staffLayouts.add(staffLayout);
                    break;
            }
        }

        return layout;
    }
}
