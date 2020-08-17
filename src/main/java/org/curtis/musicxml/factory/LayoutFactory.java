package org.curtis.musicxml.factory;

import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.MarginType;
import org.curtis.musicxml.layout.PageLayout;
import org.curtis.musicxml.layout.PageMargins;
import org.curtis.musicxml.layout.StaffLayout;
import org.curtis.musicxml.layout.SystemLayout;
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
                        pageMargins.setLeftMargin(MathUtil.newBigDecimal(XmlUtil.getChildElementText(pageMarginsElement, "left-margin")));
                        pageMargins.setRightMargin(MathUtil.newBigDecimal(XmlUtil.getChildElementText(pageMarginsElement, "right-margin")));
                        pageMargins.setTopMargin(MathUtil.newBigDecimal(XmlUtil.getChildElementText(pageMarginsElement, "top-margin")));
                        pageMargins.setBottomMargin(MathUtil.newBigDecimal(XmlUtil.getChildElementText(pageMarginsElement, "bottom-margin")));

                        String marginType = pageMarginsElement.getAttribute("type");
                        if (StringUtil.isEmpty(marginType)) marginType = "both";
                        MarginType marginTypeValue = FactoryUtil.enumValue(MarginType.class, marginType);
                        pageMargins.setType(marginTypeValue);

                        PageMargins previousPageMargins = pageMarginsMap.get(marginTypeValue);
                        if (previousPageMargins != null) System.err.println("Warning: duplicate page-margins type: " + marginTypeValue);
                        pageMarginsMap.put(marginTypeValue, pageMargins);
                    }
                    layout.setPageLayout(pageLayout);
                    break;
                case "system-layout":
                    SystemLayout systemLayout = new SystemLayout();
                    Element systemMarginsElement = XmlUtil.getChildElement(layoutSubelement, "system-margins");
                    if(systemMarginsElement != null) {
                        systemLayout.setLeftMargin(MathUtil.newBigDecimal(XmlUtil.getChildElementText(systemMarginsElement, "left-margin")));
                        systemLayout.setRightMargin(MathUtil.newBigDecimal(XmlUtil.getChildElementText(systemMarginsElement, "right-margin")));
                    }

                    systemLayout.setSystemDistance(MathUtil.newBigDecimal(XmlUtil.getChildElementText(layoutSubelement, "system-distance")));
                    systemLayout.setTopSystemDistance(MathUtil.newBigDecimal(XmlUtil.getChildElementText(layoutSubelement, "top-system-distance")));

                    Element systemDividersElement = XmlUtil.getChildElement(layoutSubelement, "system-dividers");
                    systemLayout.setLeftSystemDivider(FormattingFactory.newSystemDivider(XmlUtil.getChildElement(systemDividersElement, "left-divider")));
                    systemLayout.setRightSystemDivider(FormattingFactory.newSystemDivider(XmlUtil.getChildElement(systemDividersElement, "right-divider")));
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
