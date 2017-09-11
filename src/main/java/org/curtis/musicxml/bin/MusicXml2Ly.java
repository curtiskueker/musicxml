package org.curtis.musicxml.bin;

import org.curtis.xml.XmlException;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MusicXml2Ly {
    public void execute(String xmlFilename, String outputfilename) {
        try {
            Document xmlDocument = XmlUtil.fileToDocument(xmlFilename);
            System.err.println(XmlUtil.elementToString(xmlDocument.getDocumentElement()));
        } catch (XmlException e) {
            e.printStackTrace();
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
        musicXml2Ly.execute(xmlFilename, outputfilename);
    }
}
