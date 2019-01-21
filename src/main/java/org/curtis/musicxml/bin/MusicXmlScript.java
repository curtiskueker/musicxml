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
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;
import org.curtis.xml.SchemaValidator;
import org.curtis.xml.XmlException;
import org.curtis.xml.XmlUtil;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;

public abstract class MusicXmlScript {
    public Integer SCORE_ID;
    public String OUTPUT_FILE;
    public String INPUT_FILE;
    public String SCORE_NAME;
    public Boolean SKIP_COMMENTS = false;

    public abstract void execute() throws MusicXmlException;

    protected void setArgs(String[] args) throws MusicXmlException {
        for (String arg : args) {
            if (arg.startsWith("SCORE_ID=")) {
                String scoreId = arg.replace("SCORE_ID=", "");
                if (StringUtil.isNotEmpty(scoreId)) SCORE_ID = Integer.parseInt(scoreId);
            } else if (arg.startsWith("OUTPUT_FILE=")) {
                OUTPUT_FILE = arg.replace("OUTPUT_FILE=", "");
            } else if (arg.startsWith("INPUT_FILE=")) {
                INPUT_FILE = arg.replace("INPUT_FILE=", "");
            } else if (arg.equals("DEBUG")) {
                MusicXmlUtil.DEBUG = true;
            } else if (arg.startsWith("SCORENAME=")) {
                SCORE_NAME = arg.replace("SCORENAME=", "");
            } else if (arg.equals("SKIP_COMMENTS")) {
                SKIP_COMMENTS = true;
            } else if (arg.startsWith("SCHEMA_FILE=")) {
                MusicXmlUtil.GENERATE_SCHEMA_FILE = arg.replace("SCHEMA_FILE=", "");
            } else if (arg.equals("CREATE_SCHEMA")) {
                MusicXmlUtil.CREATE_DB_SCHEMA = true;
            }
        }

        if (StringUtil.isEmpty(OUTPUT_FILE)) {
            throw new MusicXmlException("Output file not indicated");
        }
    }

    protected Score getScoreFromDb() throws MusicXmlException {
        System.err.println("Converting dataase record to Score");
        try {
            Score score = null;
            if (SCORE_ID != null) {
                score = MusicXmlUtil.getDbTransaction().getObjectById(Score.class, SCORE_ID);
            } else if (StringUtil.isNotEmpty(SCORE_NAME)) {
                score = MusicXmlUtil.getDbTransaction().find(Score.class, "scoreName", SCORE_NAME);
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
        if (!SKIP_COMMENTS) scoreHandler.getScore().setXmlComments(MusicXmlUtil.getXmlComments(xmlDocument));

        return scoreHandler;
    }

    protected String getXmlResults(Score score) {
        System.err.println("Converting Score to XML...");
        org.curtis.musicxml.builder.ScoreBuilder scoreBuilder = new org.curtis.musicxml.builder.ScoreBuilder(score);
        String results = scoreBuilder.build().toString();

        try {
            SchemaValidator.getInstance().validate(results);
        } catch (XmlException e) {
            System.err.println(e.getMessage());
        }

        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(results.getBytes("utf-8"))));
            if (!SKIP_COMMENTS) MusicXmlUtil.setXmlComments(document, score.getXmlComments());
            results = MusicXmlUtil.getFormattedXml(document);
        } catch (Exception e) {
            // skip, use results above
        }

        return BuilderUtil.getDocumentDeclaration() + results;
    }

    protected void outputScore(Score score) throws MusicXmlException {
        try {
            ScoreBuilder scoreBuilder = new ScoreBuilder(score);
            outputResults(scoreBuilder.build().toString());
        } catch (BuildException e) {
            throw new MusicXmlException(e);
        }
    }

    protected void outputResults(String results) throws MusicXmlException {
        try {
            FileUtil.stringToFile(results, OUTPUT_FILE);
        } catch (FileException e) {
            throw new MusicXmlException(e);
        }
    }
}
