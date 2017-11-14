package org.curtis.musicxml.handler;

import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.layout.LeftRightMargins;
import org.curtis.musicxml.layout.SystemLayout;
import org.curtis.musicxml.layout.SystemMargins;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

public class PrintHandler extends AbstractHandler {
    private List<MusicData> musicDataList;

    public PrintHandler(Element element, List<MusicData> musicDataList) {
        super(element);
        this.musicDataList = musicDataList;
    }

    public void handle() {
        Print print = new Print();
        Layout layout = new Layout();

        Element systemLayoutElement = XmlUtil.getChildElement(getElement(), "system-layout");
        if(systemLayoutElement !=  null) {
            SystemLayout systemLayout = new SystemLayout();
            Element systemMarginsElement = XmlUtil.getChildElement(systemLayoutElement, "system-margins");
            if(systemMarginsElement != null) {
                SystemMargins systemMargins = new SystemMargins();
                LeftRightMargins leftRightMargins = new LeftRightMargins();
                leftRightMargins.setLeftMargin(MathUtil.newBigDecimal(XmlUtil.getChildElementText(systemMarginsElement, "left-margin")));
                leftRightMargins.setRightMargin(MathUtil.newBigDecimal(XmlUtil.getChildElementText(systemMarginsElement, "right-margin")));
                systemMargins.setLeftRightMargins(leftRightMargins);
                systemLayout.setSystemMargins(systemMargins);
            }
            systemLayout.setTopSystemDistance(MathUtil.newBigDecimal(XmlUtil.getChildElementText(systemLayoutElement, "top-system-distance")));
            layout.setSystemLayout(systemLayout);
        }

        print.setLayout(layout);

        musicDataList.add(print);
    }
}
