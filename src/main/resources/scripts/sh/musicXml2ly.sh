#!/bin/bash

# SET THIS TO DIRECTORY JAR FILE RESIDES IN
JAR_DIR=
# SET THIS TO JAR FILE NAME
JAR_FILE=musicxml-1.0-jar-with-dependencies.jar

CLASSPATH=${JAR_DIR}/${JAR_FILE}

INPUT=''
OUTPUT=''
DEBUG=''

while getopts 'di:o:' flag; do
  case "${flag}" in
    d) DEBUG="DEBUG" ;;
    i) INPUT="${OPTARG}" ;;
    o) OUTPUT="${OPTARG}" ;;
  esac
done

java -classpath ${CLASSPATH} -Dnet.sf.ehcache.enableShutdownHook=true org.curtis.musicxml.bin.MusicXml2Ly INPUT_FILE=$INPUT OUTPUT_FILE=$OUTPUT $DEBUG
