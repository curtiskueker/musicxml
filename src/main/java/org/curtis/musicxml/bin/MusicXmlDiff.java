package org.curtis.musicxml.bin;

import org.apache.xml.security.Init;
import org.apache.xml.security.c14n.Canonicalizer;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.util.FileUtil;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MusicXmlDiff extends MusicXmlScript {
    public void execute() throws MusicXmlException {
        if (!FileUtil.isMusicXmlFileExtension(getInputFile()) || !FileUtil.isMusicXmlFileExtension(getCompareFile()))
            throw new MusicXmlException("Invalid file extension");

        List<String> inputLines = getFileLines(getInputFile());
        List<String> compareLines = getFileLines(getCompareFile());

        List<String> unmatchedCompareLines = new ArrayList<>();
        for (String compareLine : compareLines) {
            String inputLine = inputLines.stream().filter(line -> line.equals(compareLine)).findFirst().orElse(null);
            if (inputLine == null) unmatchedCompareLines.add(compareLine);
            else inputLines.remove(inputLine);
        }

        for (String inputLine : inputLines) System.out.println("< " + inputLine);
        for (String unmatchedCompareLine : unmatchedCompareLines) System.out.println("> " + unmatchedCompareLine);
    }

    private List<String> getFileLines(String filename) throws MusicXmlException {
        Document document = null;
        try {
            document = XmlUtil.fileToDocument(FileUtil.getFile(filename));
        } catch (Exception e) {
            throw new MusicXmlException(e.getMessage());
        }
        String formattedDocument = getFormattedDocument(document);

        return new ArrayList<>(Arrays.asList(formattedDocument.split("\\n"))).
                stream().filter(input -> input.contains("<")).collect(Collectors.toList());
    }

    private String getFormattedDocument(Document document) throws MusicXmlException {
        try {
            Init.init();

            Canonicalizer canonicalizer = Canonicalizer.getInstance(Canonicalizer.ALGO_ID_C14N_EXCL_WITH_COMMENTS);
            byte[] canonBytes = canonicalizer.canonicalizeSubtree(document);

            Transformer transformer = MusicXmlUtil.getFormattedTransformer();
            String encoding = document.getXmlEncoding() == null ? "UTF-8" : document.getXmlEncoding();
            transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            transformer.transform(new StreamSource(new InputStreamReader(
                            new ByteArrayInputStream(canonBytes))),
                    new StreamResult(new OutputStreamWriter(outputStream)));
            return new String(outputStream.toByteArray());
        }
        catch (Exception e) {
            throw new MusicXmlException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            MusicXmlDiff musicXmlDiff = new MusicXmlDiff();
            musicXmlDiff.setOutputFileNotRequired();
            musicXmlDiff.setArgs(args);
            musicXmlDiff.execute();
        } catch (MusicXmlException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
}
