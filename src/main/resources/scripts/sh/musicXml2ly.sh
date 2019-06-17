#!/bin/bash

# SET THIS TO DIRECTORY JAR FILE RESIDES IN
JAR_DIR=
# SET THIS TO JAR FILE NAME
JAR_FILE=musicxml-1.0-jar-with-dependencies.jar

CLASSPATH=${JAR_DIR}/${JAR_FILE}

INPUT=''
OUTPUT=''
INCLUDE_BREAKS=''
DEBUG=''

while getopts 'bdi:o:' flag; do
  case "${flag}" in
    b) INCLUDE_BREAKS="INCLUDE_BREAKS" ;;
    d) DEBUG="DEBUG" ;;
    i) INPUT="${OPTARG}" ;;
    o) OUTPUT="${OPTARG}" ;;
  esac
done

java -classpath ${CLASSPATH} -Dnet.sf.ehcache.enableShutdownHook=true org.curtis.musicxml.bin.MusicXml2Ly INPUT_FILE=$INPUT OUTPUT_FILE=$OUTPUT $INCLUDE_BREAKS $DEBUG
