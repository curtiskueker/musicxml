package org.curtis.musicxml.util;

import org.curtis.database.DBException;
import org.curtis.database.DBSessionFactory;
import org.curtis.database.DBTransaction;
import org.curtis.musicxml.attributes.Attributes;
import org.curtis.musicxml.barline.Barline;
import org.curtis.musicxml.builder.ScoreBuilder;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.common.XmlComment;
import org.curtis.musicxml.direction.Direction;
import org.curtis.musicxml.direction.Grouping;
import org.curtis.musicxml.direction.Print;
import org.curtis.musicxml.direction.Sound;
import org.curtis.musicxml.direction.harmony.Harmony;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.handler.ScoreHandler;
import org.curtis.musicxml.link.Bookmark;
import org.curtis.musicxml.link.Link;
import org.curtis.musicxml.note.Backup;
import org.curtis.musicxml.note.FiguredBass;
import org.curtis.musicxml.note.Forward;
import org.curtis.musicxml.note.Note;
import org.curtis.musicxml.score.MeasureItem;
import org.curtis.musicxml.score.MusicData;
import org.curtis.musicxml.score.Score;
import org.curtis.properties.AppProperties;
import org.curtis.properties.PropertyFileNotFoundException;
import org.curtis.xml.SchemaValidator;
import org.curtis.xml.XmlException;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
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
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class MusicXmlUtil {
    private static DBSessionFactory sessionFactory;
    public static Boolean DEBUG = false;

    private MusicXmlUtil() {

    }

    public static ScoreHandler handleXmlScoreFile(File xmlFile) throws XmlException {
        Document xmlDocument = XmlUtil.fileToDocument(xmlFile);
        SchemaValidator.getInstance().validate(xmlDocument);

        ScoreHandler scoreHandler = new ScoreHandler();
        scoreHandler.handle(xmlDocument.getDocumentElement());
        scoreHandler.getScore().setXmlComments(getXmlComments(xmlDocument));

        return scoreHandler;
    }

    public static DBTransaction getDbTransaction() throws DBException {
        if (sessionFactory == null) {
            AppProperties.setPrefix("musicxml");
            try {
                AppProperties.addPropertiesFile("musicxml");
            } catch (PropertyFileNotFoundException e) {
                // optional properties file
            }
            AppProperties.addPropertiesFile("properties/database");

            sessionFactory = DBSessionFactory.getInstance();
        }

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

        return BuilderUtil.getDocumentDeclaration() + getFormattedXml(results);
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

    public static MusicData getMusicDataForMeasureItem(MeasureItem measureItem) throws MusicXmlException {
        if (measureItem == null) throw new MusicXmlException("Measure Item not found");

        Integer measureItemId = measureItem.getId();
        String musicDataType = measureItem.getMusicDataType();

        try {
            switch (musicDataType) {
                case "note":
                    return getDbTransaction().getObjectById(Note.class, measureItemId);
                case "backup":
                    return getDbTransaction().getObjectById(Backup.class, measureItemId);
                case "forward":
                    return getDbTransaction().getObjectById(Forward.class, measureItemId);
                case "direction":
                    return getDbTransaction().getObjectById(Direction.class, measureItemId);
                case "attributes":
                    return getDbTransaction().getObjectById(Attributes.class, measureItemId);
                case "harmony":
                    return getDbTransaction().getObjectById(Harmony.class, measureItemId);
                case "figured bass":
                    return getDbTransaction().getObjectById(FiguredBass.class, measureItemId);
                case "print":
                    return getDbTransaction().getObjectById(Print.class, measureItemId);
                case "sound":
                    return getDbTransaction().getObjectById(Sound.class, measureItemId);
                case "barline":
                    return getDbTransaction().getObjectById(Barline.class, measureItemId);
                case "grouping":
                    return getDbTransaction().getObjectById(Grouping.class, measureItemId);
                case "link":
                    return getDbTransaction().getObjectById(Link.class, measureItemId);
                case "bookmark":
                    return getDbTransaction().getObjectById(Bookmark.class, measureItemId);
                default:
                    throw new MusicXmlException("MeasureItem type not found");
            }
        } catch (DBException e) {
            throw new MusicXmlException(e);
        }

    }

    private static List<XmlComment> getXmlComments(Node node) {
        return getXmlComments(node, new ArrayList<String>());
    }

    private static List<XmlComment> getXmlComments(Node node, List<String> indexList) {
        List<XmlComment> xmlComments = new ArrayList<>();

        NodeList nodeList = node.getChildNodes();
        int elementCount = 0;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) elementCount++;
            if (childNode.getNodeType() == Node.COMMENT_NODE || childNode.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE) {
                String data = "";
                if (childNode.getNodeType() == Node.COMMENT_NODE) {
                    Comment comment = (Comment)childNode;
                    data = "<!-- " + comment.getData() + " -->";
                } else if (childNode.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE) {
                    ProcessingInstruction processingInstruction = (ProcessingInstruction)childNode;
                    data = "<?" + processingInstruction.getTarget() + " " + processingInstruction.getData() + "?>";
                }
                List<String> indexListCopy = new ArrayList<>(indexList);
                indexListCopy.add(String.valueOf(elementCount));
                XmlComment xmlComment = new XmlComment();
                xmlComment.setData(data);
                xmlComment.setLocation(String.join(".", indexListCopy));
                xmlComments.add(xmlComment);
            }
            if (childNode.hasChildNodes()) {
                List<String> indexListCopy = new ArrayList<>(indexList);
                indexListCopy.add(String.valueOf(elementCount));
                xmlComments.addAll(getXmlComments(childNode, indexListCopy));
            }
        }

        return xmlComments;
    }
}
