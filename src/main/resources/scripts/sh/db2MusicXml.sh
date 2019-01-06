#!/bin/bash

# SET THIS TO DIRECTORY JAR FILE RESIDES IN
JAR_DIR=
# SET THIS TO JAR FILE NAME
JAR_FILE=musicxml-1.0-jar-with-dependencies.jar

CLASSPATH=${JAR_DIR}/${JAR_FILE}

SCORE_ID=''
OUTPUT_FILE=''
SCORENAME=''
SKIP_COMMENTS=''
DEBUG=''

while getopts 'cdi:o:s:' flag; do
  case "${flag}" in
    c) SKIP_COMMENTS="SKIP_COMMENTS" ;;
    d) DEBUG="DEBUG" ;;
    i) SCORE_ID="${OPTARG}" ;;
    o) OUTPUT_FILE="${OPTARG}" ;;
    s) SCORENAME="${OPTARG}" ;;
  esac
done

java -classpath ${CLASSPATH} -Dnet.sf.ehcache.enableShutdownHook=true org.curtis.musicxml.bin.Db2MusicXml SCORENAME="$SCORENAME" SCORE_ID=$SCORE_ID OUTPUT_FILE=$OUTPUT_FILE $SKIP_COMMENTS $DEBUG
