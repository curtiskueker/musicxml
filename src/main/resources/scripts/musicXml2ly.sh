#!/bin/bash

JAR_DIR=/home/curtis/.m2/repository/org/curtis/musicxml/1.0

CLASSPATH=${JAR_DIR}/musicxml-1.0.jar

INPUT=''
OUTPUT=''

while getopts 'f:o:' flag; do
  case "${flag}" in
    f) INPUT="${OPTARG}" ;;
    o) OUTPUT="${OPTARG}" ;;
  esac
done

java -classpath ${CLASSPATH} -Dnet.sf.ehcache.enableShutdownHook=true org.curtis.musicxml.bin.MusicXml2Ly $INPUT $OUTPUT
