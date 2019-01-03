#!/bin/bash

# SET THIS TO DIRECTORY JAR FILE RESIDES IN
JAR_DIR=
# SET THIS TO JAR FILE NAME
JAR_FILE=musicxml-1.0-jar-with-dependencies.jar

CLASSPATH=${JAR_DIR}/${JAR_FILE}

SCORE_ID=''
OUTPUT_FILE=''
FILENAME=''
SKIP_COMMENTS=''
DEBUG=''

while getopts 'cdf:i:o:' flag; do
  case "${flag}" in
    c) SKIP_COMMENTS="SKIP_COMMENTS" ;;
    d) DEBUG="DEBUG" ;;
    f) FILENAME="${OPTARG}" ;;
    i) SCORE_ID="${OPTARG}" ;;
    o) OUTPUT_FILE="${OPTARG}" ;;
  esac
done

java -classpath ${CLASSPATH} -Dnet.sf.ehcache.enableShutdownHook=true org.curtis.musicxml.bin.Db2MusicXml FILENAME=$FILENAME SCORE_ID=$SCORE_ID OUTPUT_FILE=$OUTPUT_FILE $SKIP_COMMENTS $DEBUG
