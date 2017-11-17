package org.curtis.musicxml.handler;

import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.factory.LayoutFactory;
import org.curtis.musicxml.layout.Layout;
import org.curtis.musicxml.score.MusicData;
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

        print.setLayout(layout);

        musicDataList.add(print);
    }
}
