package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.database.DBSessionFactory;
import org.curtis.database.DBTransaction;
import org.curtis.musicxml.builder.ScoreBuilder;
import org.curtis.musicxml.handler.ScoreHandler;
import org.curtis.musicxml.score.Score;
import org.curtis.properties.AppProperties;
import org.curtis.properties.PropertyFileNotFoundException;
import org.curtis.xml.SchemaValidator;
import org.curtis.xml.XmlException;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;

public class MusicXmlUtil {
    private MusicXmlUtil() {

    }

    public static ScoreHandler handleXmlScoreFile(String xmlFilename) throws XmlException {
        Document xmlDocument = XmlUtil.fileToDocument(xmlFilename);
        SchemaValidator.getInstance().validate(xmlDocument);

        ScoreHandler scoreHandler = new ScoreHandler();
        scoreHandler.handle(xmlDocument.getDocumentElement());

        return scoreHandler;
    }

    public static DBTransaction getDbTransaction() throws DBException {
        AppProperties.setPrefix("musicxml");
        try {
            AppProperties.addPropertiesFile("musicxml");
        } catch (PropertyFileNotFoundException e) {
            // optional properties file
        }
        AppProperties.addPropertiesFile("properties/database");
        DBSessionFactory sessionFactory = DBSessionFactory.getInstance();

        return sessionFactory.getTransaction();
    }

    public static String getXmlResults(Score score) {
        ScoreBuilder scoreBuilder = new ScoreBuilder(score);
        String results = scoreBuilder.build().toString();

        try {
            SchemaValidator.getInstance().validate(results);
        } catch (XmlException e) {
            System.err.println(e.getMessage());
        }

        return getFormattedXml(results);
    }

    public static String getFormattedXml(String xmlString) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(xmlString.getBytes("utf-8"))));

            document.normalize();
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList) xPath.evaluate("//text()[normalize-space()='']",
                    document,
                    XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);
                node.getParentNode().removeChild(node);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 4);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StringWriter stringWriter = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return xmlString;
    }
}
