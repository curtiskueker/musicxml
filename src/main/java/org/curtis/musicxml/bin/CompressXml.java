package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.CompressedXmlUtil;
import org.curtis.xml.XmlUtil;

import java.io.FileInputStream;

public class CompressXml extends MusicXmlScript {
    public CompressXml() {

    }

    public void execute() throws MusicXmlException {
        try {
            if (StringUtil.isEmpty(getInputFile())) throw new MusicXmlException("XML filename is required.");
            if (!FileUtil.isXmlFileExtension(getInputFile())) throw new MusicXmlException("Invalid input file extension");
            if (!FileUtil.isCompressedMusicXmlFileExtension(getOutputFile())) setOutputFile(getOutputFile() + ".mxl");

            System.err.println("Compressing XML file...");

            String results = XmlUtil.readXmlToString(new FileInputStream(getInputFile()));
            CompressedXmlUtil.saveCompressedFile(getOutputFile(), getInputFile(), results);
        } catch (Exception e) {
            throw new MusicXmlException(e.getMessage());
        }
    }

}
