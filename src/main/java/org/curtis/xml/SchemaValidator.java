package org.curtis.xml;

import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.util.Objects;

public class SchemaValidator {
    private static final String SCHEMA_LOCATION = "xsd/score.xsd";

    private static SchemaValidator instance;

    private SchemaValidator() {
    }

    public static synchronized SchemaValidator getInstance() {
        if (instance == null) {
            instance = new SchemaValidator();
        }

        return instance;
    }

    public void validate(Document document) throws XmlException {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(Objects.requireNonNull(getClass().getClassLoader().getResource(SCHEMA_LOCATION)));
            Validator validator = schema.newValidator();
            XmlErrorHandler errorHandler = new XmlErrorHandler();
            validator.setErrorHandler(errorHandler);
            validator.validate(new DOMSource(document));

            if (errorHandler.hasErrors()) throw new XmlException(errorHandler.getErrors().toString());
        } catch (Exception e) {
            throw new XmlException(e.getMessage());
        }
    }
}
