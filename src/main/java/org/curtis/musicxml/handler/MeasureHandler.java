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

public class MeasureHandler extends BaseHandler {
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
            MusicData musicData = null;
            String elementName = measureSubelement.getTagName();
            switch (elementName) {
                case "note":
                    NoteHandler noteHandler = new NoteHandler();
                    musicData = noteHandler.handle(measureSubelement);
                    break;
                case "backup":
                    BackupHandler backupHandler = new BackupHandler();
                    musicData = backupHandler.handle(measureSubelement);
                    break;
                case "forward":
                    ForwardHandler forwardHandler = new ForwardHandler();
                    musicData = forwardHandler.handle(measureSubelement);
                    break;
                case "direction":
                    DirectionHandler directionHandler = new DirectionHandler();
                    musicData = directionHandler.handle(measureSubelement);
                    break;
                case "attributes":
                    AttributesHandler attributesHandler = new AttributesHandler();
                    musicData = attributesHandler.handle(measureSubelement);
                    break;
                case "harmony":
                    HarmonyHandler harmonyHandler = new HarmonyHandler();
                    musicData = harmonyHandler.handle(measureSubelement);
                    break;
                case "figured-bass":
                    musicData = NoteFactory.newFiguredBass(measureSubelement);
                    break;
                case "print":
                    PrintHandler printHandler = new PrintHandler();
                    musicData = printHandler.handle(measureSubelement);
                    break;
                case "sound":
                    musicData = DirectionFactory.newSound(measureSubelement);
                    break;
                case "barline":
                    BarlineHandler barlineHandler = new BarlineHandler();
                    musicData = barlineHandler.handle(measureSubelement);
                    break;
                case "grouping":
                    GroupingHandler groupingHandler = new GroupingHandler();
                    musicData = groupingHandler.handle(measureSubelement);
                    break;
                case "link":
                    musicData = LinkFactory.newLink(measureSubelement);
                    break;
                case "bookmark":
                    musicData = LinkFactory.newBookmark(measureSubelement);
                    break;
            }

            if(musicData != null) {
                musicDataList.add(musicData);
                musicData.setMeasure(measure);
            }
        }

        measures.add(measure);
    }
}
