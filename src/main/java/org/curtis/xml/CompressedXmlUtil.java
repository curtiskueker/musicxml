package org.curtis.xml;

import org.curtis.exception.FileException;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class CompressedXmlUtil {
    private static final String CONTAINER_FILENAME = "META-INF/container.xml";
    private static final String ROOTFILE_ELEMENT = "rootfile";
    private static final String FULL_PATH_ATTRIBUTE = "full-path";
    private static final String CONTAINER_TEMPLATE = "<container><rootfiles><rootfile full-path=\"\"></rootfile></rootfiles></container>";

    private CompressedXmlUtil() {

    }

    public static boolean isCompressedFile(String filename) {
        return FileUtil.getFileExtension(filename).equals("mxl");
    }

    public static Document getCompressedDocument(File zippedFile) throws XmlException {
        if (zippedFile == null) throw new XmlException("Invalid zipped file");

        String zippedFilename = zippedFile.getAbsolutePath();
        try {
            ZipFile zipFile = new ZipFile(zippedFile);
            ZipEntry containerEntry = zipFile.getEntry(CONTAINER_FILENAME);
            if (containerEntry == null) throw new XmlException("Container file META-INF/container.xml not found in file " + zippedFilename);

            String containerFileContents = XmlUtil.readXmlToString(zipFile.getInputStream(containerEntry));
            Document containerDocument = XmlUtil.stringToDocument(containerFileContents);
            List<Element> rootfileElements = XmlUtil.getChildElements(containerDocument.getDocumentElement(), ROOTFILE_ELEMENT);
            if (rootfileElements.isEmpty()) throw new XmlException("rootfile element not found in container file " + CONTAINER_FILENAME);

            String xmlFilename = null;
            for (Element rootfileElement : rootfileElements) {
                String filename = rootfileElement.getAttribute(FULL_PATH_ATTRIBUTE);
                if (FileUtil.isXmlFileExtension(filename)) {
                    xmlFilename = filename;
                    break;
                }
            }
            if (StringUtil.isEmpty(xmlFilename)) throw new XmlException("full-path attribute of rootfile element missing in container file " + CONTAINER_FILENAME);

            ZipEntry xmlEntry = zipFile.getEntry(xmlFilename);
            if (xmlEntry == null) throw new XmlException("Xml file " + xmlFilename + " not found in zip file " + zippedFilename);

            String xmlFileContents = XmlUtil.readXmlToString(zipFile.getInputStream(xmlEntry));
            return XmlUtil.stringToDocument(xmlFileContents);
        } catch (IOException e) {
            throw new XmlException(e.getMessage());
        }
    }

    public static void saveCompressedFile(String outputFile, String zippedFilename, String results) throws FileException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

            Document containerDocument = XmlUtil.stringToDocument(CONTAINER_TEMPLATE);
            Element containerElement = containerDocument.getDocumentElement();
            Element rootfileElement = XmlUtil.getChildElement(containerElement, "rootfile");
            rootfileElement.setAttribute("full-path", zippedFilename);
            String containerContent = XmlUtil.elementToString(containerElement);
            addZipEntry(zipOutputStream, CONTAINER_FILENAME, containerContent);

            addZipEntry(zipOutputStream, zippedFilename, results);

            zipOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            throw new FileException(e.getMessage());
        }
    }

    private static void addZipEntry(ZipOutputStream zipOutputStream, String zippedFilename, String contents) throws IOException {
        ZipEntry containerEntry = new ZipEntry(zippedFilename);
        zipOutputStream.putNextEntry(containerEntry);
        for (byte content : contents.getBytes()) {
            zipOutputStream.write(content);
        }
        zipOutputStream.closeEntry();
    }
}
