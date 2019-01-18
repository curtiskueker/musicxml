package org.curtis.musicxml.bin;

import org.curtis.database.DBException;
import org.curtis.exception.FileException;
import org.curtis.lilypond.ScoreBuilder;
import org.curtis.lilypond.exception.BuildException;
import org.curtis.musicxml.exception.MusicXmlException;
import org.curtis.musicxml.score.Score;
import org.curtis.musicxml.util.MusicXmlUtil;
import org.curtis.util.FileUtil;
import org.curtis.util.StringUtil;

public abstract class MusicXmlScript {
    public Integer SCORE_ID;
    public String OUTPUT_FILE;
    public String INPUT_FILE;
    public String SCORE_NAME;

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
                MusicXmlUtil.SKIP_COMMENTS = true;
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
