package org.curtis.musicxml.bin;

import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.util.StringUtil;
import org.curtis.xml.SchemaValidator;
import org.curtis.xml.XmlException;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Document;

import java.io.File;

public class ValidateXml extends MusicXmlScript {
    public ValidateXml() {

    }

    public void execute() throws MusicXmlException {
        try {
            if (StringUtil.isEmpty(getInputFile())) throw new MusicXmlException("XML filename is required.");

            System.err.println("Validating XML file...");

            File inputFile = new File(getInputFile());
            if (!inputFile.isFile()) throw new MusicXmlException("Unknown file: " + inputFile.getAbsolutePath());

            Document xmlDocument = XmlUtil.fileToDocument(inputFile);
            SchemaValidator.getInstance().validate(xmlDocument);

            System.err.println("Validation successful.");
        } catch (XmlException e) {
            throw new MusicXmlException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ValidateXml validateXml = new ValidateXml();
        validateXml.setOutputFileNotRequired();
        validateXml.executeScript(args);
    }
}
