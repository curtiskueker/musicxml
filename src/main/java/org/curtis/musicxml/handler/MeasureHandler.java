package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.DirectionFactory;
import org.curtis.musicxml.factory.LinkFactory;
import org.curtis.musicxml.factory.NoteFactory;
import org.curtis.musicxml.util.TypeUtil;
import org.curtis.musicxml.score.Measure;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

import java.util.List;

import static org.curtis.musicxml.util.MusicXmlUtil.DEBUG;

public class MeasureHandler implements ScoreElementHandler {
    private List<Measure> measures;

    public MeasureHandler(List<Measure> measures) {
        this.measures = measures;
    }

    public void handle(Element element) {
        String measureNumber = element.getAttribute("number");
        if (DEBUG) System.err.println("Measure " + measureNumber);
        Measure measure = new Measure();
        measure.setElementId(element.getAttribute("id"));
        measure.setNumber(measureNumber);
        measure.setText(element.getAttribute("text"));
        measure.setImplicit(TypeUtil.getYesNo(element.getAttribute("implicit")));
        measure.setNonControlling(TypeUtil.getYesNo(element.getAttribute("non-controlling")));
        measure.setWidth(MathUtil.newBigDecimal(element.getAttribute("width")));

        List<MusicData> musicDataList = measure.getMusicDataList();
        List<Element> measureSubelements = XmlUtil.getChildElements(element);

        for (Element measureSubelement : measureSubelements) {
            MusicDataHandler handler = null;
            MusicData musicData = null;
            String elementName = measureSubelement.getTagName();
            switch (elementName) {
                case "note":
                    handler = new NoteHandler();
                    break;
                case "backup":
                    handler = new BackupHandler();
                    break;
                case "forward":
                    handler = new ForwardHandler();
                    break;
                case "direction":
                    handler = new DirectionHandler();
                    break;
                case "attributes":
                    handler = new AttributesHandler();
                    break;
                case "harmony":
                    handler = new HarmonyHandler();
                    break;
                case "figured-bass":
                    musicData = NoteFactory.newFiguredBass(measureSubelement);
                    break;
                case "print":
                    handler = new PrintHandler();
                    break;
                case "sound":
                    musicData = DirectionFactory.newSound(measureSubelement);
                    break;
                case "barline":
                    handler = new BarlineHandler();
                    break;
                case "grouping":
                    handler = new GroupingHandler();
                    break;
                case "link":
                    musicData = LinkFactory.newLink(measureSubelement);
                    break;
                case "bookmark":
                    musicData = LinkFactory.newBookmark(measureSubelement);
                    break;
            }

            if (handler != null) musicData = handler.handle(measureSubelement);
            if(musicData != null) {
                musicDataList.add(musicData);
                musicData.setMeasure(measure);
            }
        }

        measures.add(measure);
    }
}
