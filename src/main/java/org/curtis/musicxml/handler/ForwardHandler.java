package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.note.Forward;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

public class ForwardHandler implements MusicDataHandler {
    public MusicData handle(Element element) {
        Forward forward = new Forward();

        forward.setDuration(MathUtil.newBigDecimal(XmlUtil.getChildElementText(element, "duration")));
        forward.setEditorial(FormattingFactory.newEditorial(element));
        forward.setVoice(XmlUtil.getChildElementText(element, "voice"));
        forward.setStaff(StringUtil.getInteger(XmlUtil.getChildElementText(element, "staff")));

        return forward;
    }
}
