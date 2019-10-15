package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.exception.FileException;
import org.curtis.lilypond.ScoreBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.builder.util.BuilderUtil;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.handler.ScoreHandler;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.properties.AppProperties;
import org.curtis.properties.PropertyException;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.SchemaValidator;
import org.curtis.xml.XmlException;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class MusicXmlScript {
    private Integer scoreId;
    private String outputFile;
    private String inputFile;
    private String scoreName;
    private Boolean skipComments = false;
    private Boolean openPdf = false;

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getScoreName() {
        return scoreName;
    }

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }

    public Boolean getSkipComments() {
        return skipComments;
    }

    public void setSkipComments(Boolean skipComments) {
        this.skipComments = skipComments;
    }

    public Boolean getOpenPdf() {
        return openPdf;
    }

    public void setOpenPdf(Boolean openPdf) {
        this.openPdf = openPdf;
    }

    public abstract void execute() throws MusicXmlException;

    protected void setArgs(String[] args) throws MusicXmlException {
        for (String arg : args) {
            if (arg.startsWith("SCORE_ID=")) {
                String scoreId = arg.replace("SCORE_ID=", "");
                if (StringUtil.isNotEmpty(scoreId)) setScoreId(Integer.parseInt(scoreId));
            } else if (arg.startsWith("OUTPUT_FILE=")) {
                setOutputFile(arg.replace("OUTPUT_FILE=", ""));
            } else if (arg.startsWith("INPUT_FILE=")) {
                setInputFile(arg.replace("INPUT_FILE=", ""));
            } else if (arg.equals("DEBUG")) {
                MusicXmlUtil.DEBUG = true;
            } else if (arg.equals("INCLUDE_BREAKS")) {
                MusicXmlUtil.INCLUDE_BREAKS = true;
            } else if (arg.startsWith("SCORENAME=")) {
                setScoreName(arg.replace("SCORENAME=", ""));
            } else if (arg.equals("SKIP_COMMENTS")) {
                setSkipComments(true);
            }
        }

        if (StringUtil.isEmpty(getOutputFile())) {
            throw new MusicXmlException("Output file not indicated");
        }
    }

    protected Score getScoreFromDb() throws MusicXmlException {
        System.err.println("Converting database record to Score...");
        try {
            Score score = null;
            if (getScoreId() != null) {
                score = MusicXmlUtil.getDbTransaction().getObjectById(Score.class, getScoreId());
            } else if (StringUtil.isNotEmpty(getScoreName())) {
                score = MusicXmlUtil.getDbTransaction().find(Score.class, "scoreName", getScoreName());
            }
            if (score == null) {
                throw new MusicXmlException("Score not found");
            }

            return score;
        } catch (DBException e) {
            e.printStackTrace();
            throw new MusicXmlException(e);
        }
    }

    protected ScoreHandler handleXmlScoreFile(File xmlFile) throws XmlException {
        System.err.println("Converting XML file to Score...");
        Document xmlDocument = XmlUtil.fileToDocument(xmlFile);
        SchemaValidator.getInstance().validate(xmlDocument);

        ScoreHandler scoreHandler = new ScoreHandler();
        scoreHandler.handle(xmlDocument.getDocumentElement());
        if (!getSkipComments()) scoreHandler.getScore().setXmlComments(MusicXmlUtil.getXmlComments(xmlDocument));

        return scoreHandler;
    }

    protected String getXmlResults(Score score) {
        System.err.println("Converting Score to XML...");
        org.curtis.musicxml.builder.ScoreBuilder scoreBuilder = new org.curtis.musicxml.builder.ScoreBuilder(score);
        String results = scoreBuilder.build().toString();

        try {
            System.err.println("Validating results...");
            SchemaValidator.getInstance().validate(results);
        } catch (XmlException e) {
            System.err.println(e.getMessage());
        }

        try {
            System.err.println("Formatting results...");
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(results.getBytes(StandardCharsets.UTF_8))));
            if (!getSkipComments()) MusicXmlUtil.setXmlComments(document, score.getXmlComments());
            results = MusicXmlUtil.getFormattedXml(document);
        } catch (Exception e) {
            // skip, use results above
        }

        return BuilderUtil.getDocumentDeclaration() + results;
    }

    protected String getLilypondFromScore(Score score) throws BuildException {
        ScoreBuilder scoreBuilder = new ScoreBuilder(score);
        return scoreBuilder.build().toString();
    }

    protected void outputLilypondResultsToFile(Score score) throws MusicXmlException {
        try {
            outputResultsToFile(getLilypondFromScore(score));
        } catch (BuildException e) {
            throw new MusicXmlException(e);
        }
    }

    protected void outputResultsToFile(String results) throws MusicXmlException {
        System.err.println("Creating Output File...");
        try {
            FileUtil.stringToFile(results, getOutputFile());
        } catch (FileException e) {
            throw new MusicXmlException(e);
        }
    }

    protected void convertLilypondToPdf() throws MusicXmlException {
        convertLilypondToPdf(null);
    }

    protected void convertLilypondToPdf(String lilypondNotation) throws MusicXmlException {
        System.err.println("Converting Lilypond notation to PDF File...");
        String lilypondLocation;
        try {
            lilypondLocation = AppProperties.getString("location.lilypond");
        } catch (PropertyException e) {
            throw new MusicXmlException("Lilypond Location not set");
        }

        List<String> lilypondCommands = new ArrayList<>();
        lilypondCommands.add(lilypondLocation);
        lilypondCommands.add("-fpdf");
        lilypondCommands.add("-o" + getOutputFile());
        if (StringUtil.isNotEmpty(lilypondNotation)) {
            lilypondCommands.add("-");
        } else if (StringUtil.isNotEmpty(getInputFile())) {
            lilypondCommands.add(getInputFile());
        } else {
            throw new MusicXmlException("Input File location or Lilypond notation source not set");
        }

        try {
            Process lilypondProcess = new ProcessBuilder(lilypondCommands).start();
            if (StringUtil.isNotEmpty(lilypondNotation)) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(lilypondProcess.getOutputStream()));
                writer.write(lilypondNotation);
                writer.flush();
                writer.close();
            }
            InputStream lilypondProcessErrorStream = lilypondProcess.getErrorStream();
            int c;
            while ((c = lilypondProcessErrorStream.read()) != -1) {
                System.err.print((char)c);
            }

            lilypondProcess.waitFor();
            int exitValue = lilypondProcess.exitValue();
            if (exitValue != 0) {
                System.err.println("Unable to convert Lilypond file to PDF file");
                return;
            }
        } catch (IOException e) {
            throw new MusicXmlException(e);
        } catch (InterruptedException e) {
            return;
        }

        if (openPdf) {
            String pdfReaderLocation = AppProperties.getOptionalProperty("location.pdfreader");
            if (StringUtil.isEmpty(pdfReaderLocation)) {
                System.err.println("Set PDF Reader Location in Set Properties to open PDF file on completion");
            } else {
                try {
                    Process pdfReaderProcess = new ProcessBuilder(pdfReaderLocation, getOutputFile() + ".pdf").start();
                    InputStream pdfReaderProcessErrorStream = pdfReaderProcess.getErrorStream();
                    int c;
                    while ((c = pdfReaderProcessErrorStream.read()) != -1) {
                        System.err.print((char)c);
                    }
                } catch (IOException e) {
                    throw new MusicXmlException(e);
                }
            }
        }
    }
}
