package org.curtis.musicxml.bin;

import org.curtis.exception.FileException;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.handler.ScoreHandler;
import org.curtis.util.FileUtil;
import org.curtis.xml.SchemaValidator;
import org.curtis.xml.XmlException;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Document;

public class MusicXml2Ly {
    private void execute(String xmlFilename, String outputFilename) throws MusicXmlException {
        try {
            Document xmlDocument = XmlUtil.fileToDocument(xmlFilename);

            // output file
            outputFilename += ".ly";

            SchemaValidator.getInstance().validate(xmlDocument);

            ScoreHandler scoreHandler = new ScoreHandler(xmlDocument.getDocumentElement());
            StringBuilder stringBuilder = scoreHandler.handle();

            FileUtil.stringToFile(stringBuilder.toString(), outputFilename);
        } catch (XmlException | FileException e) {
            throw new MusicXmlException(e);
        }
    }

    public static void main(String args[]) {
        if(args.length < 2) {
            System.err.println("Usage: mysicXml2Ly -f inputfile -o outputfile");
            return;
        }

        String xmlFilename = args[0];
        String outputfilename = args[1];

        MusicXml2Ly musicXml2Ly = new MusicXml2Ly();
        try {
            musicXml2Ly.execute(xmlFilename, outputfilename);
        } catch (MusicXmlException e) {
            throw new RuntimeException(e);
        }
    }
}
