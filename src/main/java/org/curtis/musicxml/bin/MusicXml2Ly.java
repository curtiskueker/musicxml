package org.curtis.musicxml.bin;

import org.curtis.exception.FileException;
import org.curtis.musicxml.MusicXmlException;
import org.curtis.util.FileUtil;
import org.curtis.xml.SchemaValidator;
import org.curtis.xml.XmlException;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MusicXml2Ly {
    public void execute(String xmlFilename, String outputFilename) throws MusicXmlException {
        try {
            Document xmlDocument = XmlUtil.fileToDocument(xmlFilename);

            // output file
            if (outputFilename.indexOf(".") > 0) {
                outputFilename = outputFilename.substring(0, outputFilename.lastIndexOf("."));
            }
            outputFilename += ".xml";

            SchemaValidator.getInstance().validate(xmlDocument);

            FileUtil.stringToFile(XmlUtil.elementToString(xmlDocument.getDocumentElement()), outputFilename);
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
