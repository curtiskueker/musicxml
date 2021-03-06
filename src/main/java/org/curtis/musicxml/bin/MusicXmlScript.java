package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.exception.FileException;
import org.curtis.lilypond.ScoreBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.common.XmlComment;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.handler.ScoreHandler;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.score.ScoreType;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.properties.PropertiesHandler;
import org.curtis.properties.PropertiesConstants;
import org.curtis.properties.PropertyException;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.CompressedXmlUtil;
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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public abstract class MusicXmlScript {
    private Integer scoreId;
    private String outputFile;
    private String inputFile;
    private String compareFile;
    private String scoreName;
    private Boolean skipComments = false;
    private String zippedFile;
    private Boolean openPdf = false;
    private Boolean outputFileRequired = true;

    private static final int COMMENTS_THRESHOLD = 250;
    private static final String STDOUT = "STDOUT";

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

    public String getCompareFile() {
        return compareFile;
    }

    public void setCompareFile(String compareFile) {
        this.compareFile = compareFile;
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

    public String getZippedFile() {
        return zippedFile;
    }

    public void setZippedFile(String zippedFile) {
        this.zippedFile = zippedFile;
    }

    public Boolean getOpenPdf() {
        return openPdf;
    }

    public void setOpenPdf(Boolean openPdf) {
        this.openPdf = openPdf;
    }

    protected boolean isStdOut() {
        return StringUtil.nullToString(getOutputFile()).equals(STDOUT);
    }

    protected void setOutputFileNotRequired() {
        outputFileRequired = false;
    }

    protected boolean outputFileRequired() {
        return outputFileRequired;
    }

    protected void executeScript(String[] args) {
        try {
            setArgs(args);
            execute();
            System.err.println("Script finished successfully");
        } catch (MusicXmlException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        } catch (Throwable e){
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }

    public abstract void execute() throws MusicXmlException;

    protected void setArgs(String[] args) throws MusicXmlException {
        PropertiesHandler.SHOW_SQL = false;

        for (String arg : args) {
            if (arg.startsWith("SCORE_ID=")) {
                String scoreId = arg.replace("SCORE_ID=", "");
                if (StringUtil.isNotEmpty(scoreId)) setScoreId(Integer.parseInt(scoreId));
            } else if (arg.startsWith("OUTPUT_FILE=")) {
                setOutputFile(arg.replace("OUTPUT_FILE=", ""));
            } else if (arg.startsWith("INPUT_FILE=")) {
                setInputFile(arg.replace("INPUT_FILE=", ""));
            } else if (arg.startsWith("ZIPPED_FILE=")) {
                setZippedFile(arg.replace("ZIPPED_FILE=", ""));
            } else if (arg.startsWith("COMPARE_FILE=")) {
                setCompareFile(arg.replace("COMPARE_FILE=", ""));
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

        if (outputFileRequired() && StringUtil.isEmpty(getOutputFile())) {
            throw new MusicXmlException("Output file not indicated");
        }
    }

    protected Score getScoreFromDb() throws MusicXmlException {
        System.err.println("Converting database record to Score...");
        try {
            Score score = null;
            if (getScoreId() != null) {
                score = MusicXmlUtil.getNewDbTransaction().getObjectById(Score.class, getScoreId());
            } else if (StringUtil.isNotEmpty(getScoreName())) {
                score = MusicXmlUtil.getNewDbTransaction().find(Score.class, "scoreName", getScoreName());
            }
            if (score == null) {
                throw new MusicXmlException("Score not found");
            }

            return score;
        } catch (DBException e) {
            throw new MusicXmlException(e.getMessage());
        }
    }

    protected ScoreHandler handleXmlScoreFile(File xmlFile) throws XmlException {
        System.err.println("Converting XML file to Score...");
        Document document = XmlUtil.fileToDocument(xmlFile);
        SchemaValidator.getInstance().validate(document);

        ScoreHandler scoreHandler = new ScoreHandler();
        scoreHandler.handleDeclaration(document);

        ScoreType scoreType;
        switch (document.getDocumentElement().getTagName()) {
            case MusicXmlUtil.SCORE_PARTWISE:
                scoreType = ScoreType.PARTWISE;
                break;
            case MusicXmlUtil.SCORE_TIMEWISE:
                scoreType = ScoreType.TIMEWISE;
                document = XmlUtil.transformDocument(document, "xsl/timepart.xsl");
                break;
            default:
                throw new XmlException("Unrecognized root element: " + document.getDocumentElement().getTagName());
        }

        scoreHandler.handle(document.getDocumentElement());
        scoreHandler.getScore().setScoreType(scoreType);
        if (!getSkipComments()) scoreHandler.getScore().setXmlComments(MusicXmlUtil.getXmlComments(document));

        return scoreHandler;
    }

    protected String getXmlResults(Score score) {
        System.err.println("Converting Score to XML...");
        org.curtis.musicxml.builder.ScoreBuilder scoreBuilder = new org.curtis.musicxml.builder.ScoreBuilder(score);
        String results = scoreBuilder.build().toString();

        try {
            byte[] resultBytes = results.getBytes(scoreBuilder.getEncoding());
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            builderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            Document document = builderFactory.newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(resultBytes)));

            try {
                System.err.println("Validating results...");
                SchemaValidator.getInstance().validate(document);
            } catch (XmlException e) {
                e.printStackTrace();
            }

            System.err.println("Formatting results...");
            if (!getSkipComments()) {
                List<XmlComment> scoreComments = score.getXmlComments();
                if (scoreComments.size() > COMMENTS_THRESHOLD) MusicXmlUtil.setXmlCommentsByWalk(document, scoreComments);
                else MusicXmlUtil.setXmlCommentsByXpath(document, scoreComments);
            }

            if (score.getScoreType() == ScoreType.TIMEWISE) document = XmlUtil.transformDocument(document, "xsl/parttime.xsl");

            return MusicXmlUtil.getFormattedXml(document, score);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    protected String getLilypondFromScore(Score score) throws BuildException {
        ScoreBuilder scoreBuilder = new ScoreBuilder(score);
        return scoreBuilder.build().toString();
    }

    protected void outputLilypondResultsToFile(Score score) throws MusicXmlException {
        try {
            outputResultsToFile(getLilypondFromScore(score));
        } catch (BuildException e) {
            throw new MusicXmlException(e.getMessage());
        }
    }

    protected void outputResultsToFile(String results) throws MusicXmlException {
        outputResultsToFile(results, Score.DEFAULT_CHARSET);
    }

    protected void outputResultsToFile(String results, Charset charset) throws MusicXmlException {
        System.err.println("Creating Output File...");
        try {
            if (isStdOut()) System.out.print(results);
            else if (FileUtil.isCompressedMusicXmlFileExtension(getOutputFile())) CompressedXmlUtil.saveCompressedFile(getOutputFile(), getZippedFile(), results);
            else FileUtil.stringToFile(results, getOutputFile(), charset);
        } catch (FileException e) {
            throw new MusicXmlException(e.getMessage());
        }
    }

    protected void convertLilypondToPdf() throws MusicXmlException {
        convertLilypondToPdf(null);
    }

    protected void convertLilypondToPdf(String lilypondNotation) throws MusicXmlException {
        if (StringUtil.isEmpty(getOutputFile())) throw new MusicXmlException("Empty output filename.");
        if (FileUtil.isPdfFileExtension(getOutputFile())) setOutputFile(getOutputFile().substring(0, getOutputFile().lastIndexOf(".")));
        System.err.println("Converting Lilypond notation to PDF File...");
        String lilypondLocation;
        try {
            PropertiesHandler.addLocalProperties();
            lilypondLocation = PropertiesHandler.getString(PropertiesConstants.LILYPOND_LOCATION);
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
            throw new MusicXmlException(e.getMessage());
        } catch (InterruptedException e) {
            return;
        }

        if (openPdf) {
            Runnable pdfRunnable = this::openReader;
            Thread pdfThread = new Thread(pdfRunnable);
            pdfThread.start();
        }
    }

    private void openReader() {
        String pdfReaderLocation = PropertiesHandler.getOptionalProperty(PropertiesConstants.PDF_LOCATION);
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
                System.err.println(e.getMessage());
            }
        }
    }
}
