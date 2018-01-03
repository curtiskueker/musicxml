package org.curtis.musicxml.handler;

import org.curtis.musicxml.factory.FormattingFactory;
import org.curtis.musicxml.note.Backup;
import org.curtis.musicxml.score.MusicData;
import org.curtis.util.MathUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Element;

public class BackupHandler extends MusicDataHandler {
    public BackupHandler() {

    }

    public MusicData handle(Element element) {
        Backup backup = new Backup();

        backup.setDuration(MathUtil.newBigDecimal(XmlUtil.getChildElementText(element, "duration")));
        backup.setEditorial(FormattingFactory.newEditorial(element));

        return backup;
    }
}
