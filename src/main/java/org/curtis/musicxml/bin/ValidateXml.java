package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.xml.SchemaValidator;
import org.curtis.xml.XmlException;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Document;

import java.io.File;

public class ValidateXml extends MusicXmlScript {
    private String xmlFileLocation;

    public ValidateXml() {

    }

    public String getXmlFileLocation() {
        return xmlFileLocation;
    }

    public void setXmlFileLocation(String xmlFileLocation) {
        this.xmlFileLocation = xmlFileLocation;
    }

    public void execute() throws MusicXmlException {
        try {
            System.err.println("Validating XML file...");

            File inputFile = new File(getXmlFileLocation());
            if (!inputFile.isFile()) throw new MusicXmlException("Unknown file: " + inputFile.getAbsolutePath());
            Document xmlDocument = XmlUtil.fileToDocument(inputFile);
            SchemaValidator.getInstance().validate(xmlDocument);

            System.err.println("Validation successful.");
        } catch (XmlException e) {
            throw new MusicXmlException(e.getMessage());
        }
    }
}
