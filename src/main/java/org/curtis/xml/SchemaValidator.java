package org.curtis.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;

public class SchemaValidator {
    private static SchemaValidator instance;

    private SchemaValidator() {
    }

    public static synchronized SchemaValidator getInstance() {
        if (instance == null) {
            instance = new SchemaValidator();
        }

        return instance;
    }

    public void validate(String documentEleent) throws XmlException {
        try {
            String schemaLocation = "musicxml/xsd/score.xsd";

            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = schemaFactory.newSchema(getClass().getClassLoader().getResource(schemaLocation));
            Source xmlFile = new StreamSource(new ByteArrayInputStream(documentEleent.getBytes()));
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
        } catch (Exception e) {
            throw new XmlException(e);
        }
    }

    public void validate(Document document) throws XmlException {
        Element documentElement = document.getDocumentElement();
        validate(XmlUtil.elementToString(documentElement));
    }
}
