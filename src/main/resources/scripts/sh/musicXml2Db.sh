#!/bin/bash

# SET THIS TO DIRECTORY JAR FILE RESIDES IN
JAR_DIR=
# SET THIS TO JAR FILE NAME
JAR_FILE=musicxml-1.0-jar-with-dependencies.jar

CLASSPATH=${JAR_DIR}/${JAR_FILE}

CREATE_SCHEMA=''
SCHEMA_FILE=''
INPUT_FILE=''

while getopts 'sf:i:' flag; do
  case "${flag}" in
    i) INPUT_FILE="${OPTARG}" ;;
    f) SCHEMA_FILE="${OPTARG}" ;;
    s) CREATE_SCHEMA="CREATE_SCHEMA" ;;
  esac
done

java -classpath ${CLASSPATH} -Dnet.sf.ehcache.enableShutdownHook=true org.curtis.musicxml.bin.MusicXml2Db SCHEMA_FILE=$SCHEMA_FILE INPUT_FILE=$INPUT_FILE $CREATE_SCHEMA
