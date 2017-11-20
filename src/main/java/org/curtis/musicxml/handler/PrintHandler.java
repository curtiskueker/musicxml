package org.curtis.musicxml.handler;

import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.factory.LayoutFactory;
import org.curtis.musicxml.handler.util.TypeUtil;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.w3c.dom.Element;

import java.util.List;

public class PrintHandler extends MusicDataHandler {
    public PrintHandler() {

    }

    public MusicData handle(Element element) {
        Print print = new Print();
        Layout layout = LayoutFactory.newLayout(element);

        print.setStaffSpacing(MathUtil.newBigDecimal(element.getAttribute("staff-spacing")));
        print.setNewSystem(TypeUtil.getYesNo(element.getAttribute("new-system")));
        print.setNewPage(TypeUtil.getYesNo(element.getAttribute("new-page")));
        print.setBlankPage(StringUtil.getInteger(element.getAttribute("blank-page")));
        print.setPageNumber(element.getAttribute("page-number"));

        print.setLayout(layout);

        return print;
    }
}
