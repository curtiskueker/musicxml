#!/bin/bash

# SET THIS TO DIRECTORY JAR FILE RESIDES IN
JAR_DIR=
# SET THIS TO JAR FILE NAME
JAR_FILE=musicxml-1.0-jar-with-dependencies.jar

CLASSPATH=${JAR_DIR}/${JAR_FILE}

INPUT_FILE=''
SCORENAME=''
DEBUG=''

while getopts 'di:s:' flag; do
  case "${flag}" in
    d) DEBUG="DEBUG" ;;
    i) INPUT_FILE="${OPTARG}" ;;
    s) SCORENAME="${OPTARG}" ;;
  esac
done

java -classpath ${CLASSPATH} -Dnet.sf.ehcache.enableShutdownHook=true org.curtis.musicxml.bin.MusicXml2Db INPUT_FILE=$INPUT_FILE SCORENAME="$SCORENAME" $DEBUG
