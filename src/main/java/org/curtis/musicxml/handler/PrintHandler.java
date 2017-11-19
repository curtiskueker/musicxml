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

public class PrintHandler extends AbstractHandler {
    private List<MusicData> musicDataList;

    public PrintHandler(Element element, List<MusicData> musicDataList) {
        super(element);
        this.musicDataList = musicDataList;
    }

    public void handle() {
        Print print = new Print();
        Layout layout = LayoutFactory.newLayout(getElement());

        print.setStaffSpacing(MathUtil.newBigDecimal(getElement().getAttribute("staff-spacing")));
        print.setNewSystem(TypeUtil.getYesNo(getElement().getAttribute("new-system")));
        print.setNewPage(TypeUtil.getYesNo(getElement().getAttribute("new-page")));
        print.setBlankPage(StringUtil.getInteger(getElement().getAttribute("blank-page")));
        print.setPageNumber(getElement().getAttribute("page-number"));

        print.setLayout(layout);

        musicDataList.add(print);
    }
}
