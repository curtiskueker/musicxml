#!/bin/bash

# SET THIS TO DIRECTORY JAR FILE RESIDES IN
JAR_DIR=
# SET THIS TO JAR FILE NAME
JAR_FILE=musicxml-1.0-jar-with-dependencies.jar

CLASSPATH=${JAR_DIR}/${JAR_FILE}

CREATE_SCHEMA=''
SCHEMA_FILE=''
INPUT_FILE=''
SCORENAME=''
DEBUG=''

while getopts 'di:f:gs:' flag; do
  case "${flag}" in
    d) DEBUG="DEBUG" ;;
    i) INPUT_FILE="${OPTARG}" ;;
    f) SCHEMA_FILE="${OPTARG}" ;;
    g) CREATE_SCHEMA="CREATE_SCHEMA" ;;
    s) SCORENAME="${OPTARG}" ;;
  esac
done

java -classpath ${CLASSPATH} -Dnet.sf.ehcache.enableShutdownHook=true org.curtis.musicxml.bin.MusicXml2Db SCHEMA_FILE=$SCHEMA_FILE INPUT_FILE=$INPUT_FILE SCORENAME="$SCORENAME" $CREATE_SCHEMA $DEBUG
