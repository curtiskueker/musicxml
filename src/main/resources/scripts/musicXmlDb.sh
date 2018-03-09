#!/bin/bash

JAR_DIR=/home/curtis/.m2/repository/org/curtis/musicxml/1.0

CLASSPATH=${JAR_DIR}/musicxml-1.0-jar-with-dependencies.jar

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

java -classpath ${CLASSPATH} -Dnet.sf.ehcache.enableShutdownHook=true org.curtis.musicxml.bin.MusicXmlDb SCHEMA_FILE=$SCHEMA_FILE INPUT_FILE=$INPUT_FILE $CREATE_SCHEMA
