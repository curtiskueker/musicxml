package org.curtis.musicxml.factory;

import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.MarginType;
import org.curtis.musicxml.layout.Margins;
import org.curtis.musicxml.layout.PageLayout;
import org.curtis.musicxml.layout.PageMargins;
import org.curtis.musicxml.layout.SystemLayout;
import org.curtis.musicxml.layout.SystemMargins;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

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
                    List<PageMargins> pageMarginsList = pageLayout.getPageMarginsList();
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

                        pageMarginsList.add(pageMargins);
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

                    layout.setSystemLayout(systemLayout);
                    break;
                case "staff-layout":
                    break;
            }
        }

        return layout;
    }
}
